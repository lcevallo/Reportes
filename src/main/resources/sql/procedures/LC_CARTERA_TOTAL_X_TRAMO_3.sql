SET ANSI_NULLS ON
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE dbo.LC_CARTERA_TOTAL_X_TRAMO_3
(
 @ACCOUNTNUM nvarchar(20)=NULL,
 @GRUPODIAS varchar(22)=NULL
)
AS
BEGIN
DECLARE @suma numeric(32, 16);
  SET NOCOUNT ON;

   IF OBJECT_ID('tempdb..#TMP_CARTERA_TOTAL_X_TRAMO_1') IS NOT NULL
    BEGIN
        DROP TABLE #TMP_CARTERA_TOTAL_X_TRAMO_1
    END


   IF OBJECT_ID('tempdb..#TMP_CARTERA_TOTAL_PVT') IS NOT NULL
    BEGIN
        DROP TABLE #TMP_CARTERA_TOTAL_PVT
    END

 -- Con esto saco el valor total de la FACTURA
/*
SELECT
CustInvoiceJour.INVOICEAMOUNT,
CustInvoiceJour.INVOICEID
FROM
  CustInvoiceJour
  where
    CustInvoiceJour.INVOICEID='F_001001000001745'
*/

CREATE TABLE #TMP_CARTERA_TOTAL_PVT
(
ID int primary key IDENTITY(1,1) NOT NULL,
ACCOUNTNUM nvarchar(20)  NOT NULL,
NOMBRE_CLIENTE nvarchar(100),
[YA VENCIDOS] numeric(32, 16) ,
[15 dias] numeric(32, 16),
[30 dias] numeric(32, 16),
[45 dias] numeric(32, 16),
[60 dias] numeric(32, 16),
[90 dias] numeric(32, 16),
[120 dias] numeric(32, 16),
[Mayores a 120 dias] numeric(32, 16)
)


CREATE TABLE #TMP_CARTERA_TOTAL_X_TRAMO_1 (
  ID int primary key IDENTITY(1,1) NOT NULL,
  ACCOUNTNUM nvarchar(20)  NOT NULL,
  NOMBRE_CLIENTE nvarchar(100),
  INVOICE nvarchar(20)  NULL,
  TIPO varchar(22)  NULL,
  dias_vencido int NULL,
  GRUPODIAS varchar(22) NULL,
  TRANSDATE datetime NOT NULL,
  DUEDATE datetime NOT NULL,
  DiffDate int NULL,
  VOUCHER nvarchar(20)  NOT NULL,
  AMOUNTCUR numeric(32, 16) NOT NULL,
  OFFSETTRANSVOUCHER nvarchar(20) NULL,
  TRANSDATE2 datetime NULL,
  INVOICE2 nvarchar(20)  NULL,
  DUEDATE2 datetime NULL,
  PAYMENT nvarchar(10) NULL,
  PAYMENTSCHED nvarchar(30) NULL,
  PRICEGROUPID nvarchar(10) NULL
)


INSERT INTO
  #TMP_CARTERA_TOTAL_X_TRAMO_1(
  ACCOUNTNUM,
  NOMBRE_CLIENTE,
  INVOICE,
  TIPO,
  dias_vencido,
  GRUPODIAS,
  TRANSDATE,
  DUEDATE,
  DiffDate,
  VOUCHER,
  AMOUNTCUR
  )
  SELECT
  CusttransOpen.ACCOUNTNUM,
   (
  SELECT
   DIRPARTYTABLE.NAME
FROM
  CUSTTABLE
  LEFT OUTER JOIN DIRPARTYTABLE ON (DIRPARTYTABLE.RECID = CUSTTABLE.PARTY)
WHERE
  CUSTTABLE.ACCOUNTNUM=CusttransOpen.ACCOUNTNUM
  and
  CUSTTABLE.DATAAREAID=CusttransOpen.DATAAREAID
  ) as nombre_cliente,
  Custtrans.INVOICE,
  TIPO = CASE
  WHEN (Custtrans.Voucher LIKE 'ADLCC%') THEN 'CHEQUE POSTFECHADO'
  WHEN (Custtrans.Voucher LIKE 'ADNDCL%') THEN 'NOTA DE DEBITO INTERNA'
  ELSE 'FACTURA NO DOCUMENTADA' END,
   datediff(day,GETDATE(), custtransopen.duedate) dias_vencido,
   GRUPODIAS= CASE
   WHEN datediff(day,GETDATE(), custtransopen.duedate) <= 0 THEN 'YA VENCIDOS'
   WHEN datediff(day,GETDATE(), custtransopen.duedate) BETWEEN 1 AND 15 THEN '15 dias'
   WHEN datediff(day,GETDATE(), custtransopen.duedate) BETWEEN 16 AND 30  THEN '30 dias'
   WHEN datediff(day,GETDATE(), custtransopen.duedate) BETWEEN 31 AND 45 THEN '45 dias'
   WHEN datediff(day,GETDATE(), custtransopen.duedate) BETWEEN 46 AND 60 THEN '60 dias'
   WHEN datediff(day,GETDATE(), custtransopen.duedate) BETWEEN 61 AND 90 THEN '90 dias'
   WHEN datediff(day,GETDATE(), custtransopen.duedate) BETWEEN 91 AND 120 THEN '120 dias'
   WHEN datediff(day,GETDATE(), custtransopen.duedate) > 120 THEN 'Mayores a 120 dias'
   END,
  CusttransOpen.TRANSDATE,
  CusttransOpen.DUEDATE,
  DATEDIFF(day,CusttransOpen.TRANSDATE,CusttransOpen.DUEDATE) AS DiffDate,
  Custtrans.VOUCHER,
  CusttransOpen.AMOUNTCUR
FROM
 CusttransOpen
  INNER JOIN Custtrans ON (CusttransOpen.ACCOUNTNUM = Custtrans.ACCOUNTNUM)
  AND (CusttransOpen.REFRECID = Custtrans.RECID)

WHERE
  CusttransOpen.DATAAREAID = 'alph'
  --AND
  --CUSTTRANSOPEN.ACCOUNTNUM = 'C000035';

UPDATE TBLORIGINAL
SET TBLORIGINAL.OFFSETTRANSVOUCHER= tbl2.OFFSETTRANSVOUCHER
FROM #TMP_CARTERA_TOTAL_X_TRAMO_1 TBLORIGINAL
INNER JOIN
(
SELECT
CUSTSETTLEMENT.OFFSETTRANSVOUCHER,Custtrans.VOUCHER
FROM
  custtrans
  INNER JOIN CUSTSETTLEMENT ON (custtrans.DATAAREAID = CUSTSETTLEMENT.TRANSCOMPANY)
  AND (custtrans.RECID = CUSTSETTLEMENT.TRANSRECID)
  AND (custtrans.ACCOUNTNUM = CUSTSETTLEMENT.ACCOUNTNUM)
  WHERE
 -- Custtrans.VOUCHER=TBLORIGINAL.VOUCHER AND
  custtrans.DATAAREAID='alph'
) tbl2 ON
TBLORIGINAL.VOUCHER=tbl2.VOUCHER
WHERE
TBLORIGINAL.TIPO='CHEQUE POSTFECHADO' OR
TBLORIGINAL.TIPO='NOTA DE DEBITO INTERNA'


UPDATE TBLORIGINAL
SET TBLORIGINAL.INVOICE2=tbl2.invoice
, TBLORIGINAL.TRANSDATE2=tbl2.TRANSDATE,
TBLORIGINAL.DUEDATE2=tbl2.DUEDATE
FROM #TMP_CARTERA_TOTAL_X_TRAMO_1 TBLORIGINAL
INNER JOIN
Custtrans tbl2
ON tbl2.ACCOUNTNUM=TBLORIGINAL.ACCOUNTNUM
WHERE
tbl2.DATAAREAID='alph' AND
tbl2.VOUCHER=TBLORIGINAL.OFFSETTRANSVOUCHER;


UPDATE TBLORIGINAL
SET
TBLORIGINAL.PAYMENT=tblSales.PAYMENT,
TBLORIGINAL.PAYMENTSCHED=tblSales.PaymentSched,
TBLORIGINAL.PRICEGROUPID=tblSales.PriceGroupId
FROM #TMP_CARTERA_TOTAL_X_TRAMO_1 TBLORIGINAL
INNER JOIN
(
SELECT
  dbo.SALESTABLE.PAYMENT,
  dbo.SALESTABLE.PaymentSched,
  dbo.SALESTABLE.PriceGroupId,
  CUSTINVOICEJOUR.INVOICEID
FROM
  CUSTINVOICEJOUR
  LEFT OUTER JOIN dbo.SALESTABLE ON (CUSTINVOICEJOUR.SALESID = dbo.SALESTABLE.SALESID)
WHERE
  CUSTINVOICEJOUR.DATAAREAID = 'alph'
  ) tblSales
  ON tblSales.INVOICEID=TBLORIGINAL.INVOICE
  WHERE
  TBLORIGINAL.TIPO LIKE 'FACT%'




UPDATE TBLORIGINAL
SET
TBLORIGINAL.PAYMENT=tblSales.PAYMENT,
TBLORIGINAL.PAYMENTSCHED=tblSales.PaymentSched,
TBLORIGINAL.PRICEGROUPID=tblSales.PriceGroupId
FROM #TMP_CARTERA_TOTAL_X_TRAMO_1 TBLORIGINAL
INNER JOIN
(
SELECT
  dbo.SALESTABLE.PAYMENT,
  dbo.SALESTABLE.PaymentSched,
  dbo.SALESTABLE.PriceGroupId,
  CUSTINVOICEJOUR.INVOICEID
FROM
  CUSTINVOICEJOUR
  LEFT OUTER JOIN dbo.SALESTABLE ON (CUSTINVOICEJOUR.SALESID = dbo.SALESTABLE.SALESID)
WHERE
  CUSTINVOICEJOUR.DATAAREAID = 'alph'
  ) tblSales
  ON tblSales.INVOICEID=TBLORIGINAL.INVOICE2
  WHERE
  TBLORIGINAL.TIPO NOT LIKE 'FACT%'


if @ACCOUNTNUM IS NOT NULL
    BEGIN
        Select *  from #TMP_CARTERA_TOTAL_X_TRAMO_1 where ACCOUNTNUM=@ACCOUNTNUM AND GRUPODIAS=@GRUPODIAS; ;

    END
ELSE
    BEGIN
    	INSERT INTO #TMP_CARTERA_TOTAL_PVT
        (
        	ACCOUNTNUM,
            [YA VENCIDOS] ,
            [15 dias] ,
            [30 dias] ,
            [45 dias] ,
            [60 dias] ,
            [90 dias] ,
            [120 dias],
            [Mayores a 120 dias]
        )
        SELECT * FROM
                        (SELECT
                          ACCOUNTNUM,
                          GRUPODIAS,
                          AMOUNTCUR
                        FROM #TMP_CARTERA_TOTAL_X_TRAMO_1

                        ) as s
                        PIVOT
                        (
                          SUM(AMOUNTCUR)
                          FOR GRUPODIAS IN ([YA VENCIDOS],[15 dias],[30 dias],[45 dias],[60 dias],[90 dias],[120 dias],[Mayores a 120 dias])

                        )AS PVT


       UPDATE TBL1
       SET TBL1.NOMBRE_CLIENTE=TBL2.NOMBRE_CLIENTE
       FROM #TMP_CARTERA_TOTAL_PVT TBL1
       INNER JOIN
       (
         Select DISTINCT ACCOUNTNUM,NOMBRE_CLIENTE
          from
          #TMP_CARTERA_TOTAL_X_TRAMO_1
       ) TBL2 ON
       TBL1.ACCOUNTNUM=TBL2.ACCOUNTNUM;

       Select *  from  #TMP_CARTERA_TOTAL_PVT;

    END



DROP TABLE #TMP_CARTERA_TOTAL_X_TRAMO_1;
DROP TABLE #TMP_CARTERA_TOTAL_PVT
END
