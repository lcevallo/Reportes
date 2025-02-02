SET ANSI_NULLS ON
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE dbo.LC_ReporteVentas
(
@fechaInicial DATE,
@fechaFinal DATE
)

AS
BEGIN

DECLARE
@INVOICEID nvarchar(20),
@ITEMID nvarchar(20),
@LEDGERVOUCHER nvarchar(20),
@QTY integer,
@RECID bigint,
@COSTO numeric(32, 16)

  SET NOCOUNT ON;

    -- Real trmediate result

 IF OBJECT_ID('tempdb..#TABLAREPORTE') IS NOT NULL
BEGIN
    DROP TABLE #TABLAREPORTE
END

 IF OBJECT_ID('tempdb..#TemporalInventtrans') IS NOT NULL
BEGIN
    DROP TABLE #TemporalInventtrans
END

 IF OBJECT_ID('tempdb..#tblReporteVentas') IS NOT NULL
BEGIN
    DROP TABLE #tblReporteVentas
END

CREATE TABLE #TemporalInventtrans
(
--cantidad int,
COSTAMOUNTPOSTED numeric(32, 16) DEFAULT 0 NOT NULL,
COSTAMOUNTADJUSTMENT numeric(32, 16) DEFAULT 0 NOT NULL
--ITEMID nvarchar(20)

);

CREATE TABLE #tblReporteVentas (
  ID int primary key IDENTITY(1,1) NOT NULL,
  NoDoc nvarchar(20) COLLATE Latin1_General_CI_AS NOT NULL,
  FechaDoc datetime NOT NULL,
  CodCliente nvarchar(20) COLLATE Latin1_General_CI_AS NOT NULL,
  NombCliente nvarchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
  CodItem nvarchar(20) COLLATE Latin1_General_CI_AS NOT NULL,
  Diario nvarchar(20) COLLATE Latin1_General_CI_AS NOT NULL,
  Descripcion nvarchar(1000) COLLATE Latin1_General_CI_AS NOT NULL,
  Cantidad numeric(32, 16) NOT NULL,
  PrecioUnitario numeric(32, 16) NOT NULL,
  Neto numeric(32, 16) NOT NULL,
  costo numeric(32, 16) NULL,
  vendedor nvarchar(100) COLLATE Latin1_General_CI_AS NULL
)


    SELECT
  dbo.CUSTINVOICETRANS.INVOICEID as NoDoc,
  dbo.CUSTINVOICEJOUR.INVOICEACCOUNT AS CodCliente,
  dbo.CUSTINVOICEJOUR.INVOICINGNAME AS NombCliente,
  dbo.CUSTINVOICETRANS.ITEMID AS CodItem,
  dbo.CUSTINVOICEJOUR.LEDGERVOUCHER As Diario,
  dbo.CUSTINVOICETRANS.NAME As Descripcion,
  dbo.CUSTINVOICETRANS.QTY as Cantidad,
  dbo.CUSTINVOICETRANS.SALESPRICE AS PrecioUnitario,
  dbo.CUSTINVOICETRANS.LINEAMOUNT as Neto,
  dbo.CUSTINVOICETRANS.INVOICEDATE as FechaDoc,
  dbo.CUSTINVOICEJOUR.SALESID as NoVenta,
  CUSTINVOICETRANS.INVENTTRANSID,
  INVENTTRANSORIGIN.RECID,
  convert(NUMERIC(32,16),0) as costo,
   (SELECT DEFAULTDIMENSIONVIEW.DISPLAYVALUE FROM DEFAULTDIMENSIONVIEW INNER JOIN CUSTTABLE ON (DEFAULTDIMENSIONVIEW.DEFAULTDIMENSION = CUSTTABLE.DEFAULTDIMENSION) WHERE CUSTINVOICEJOUR.INVOICEACCOUNT = CUSTTABLE.ACCOUNTNUM AND DEFAULTDIMENSIONVIEW.NAME = 'CANAL_ALPH') AS canal,
    (
  SELECT dbo.DIRPARTYTABLE.NAME
FROM
  dbo.HCMWORKER
  FULL OUTER JOIN dbo.DIRPARTYTABLE ON (dbo.HCMWORKER.PERSON = dbo.DIRPARTYTABLE.RECID)
WHERE
  dbo.HCMWORKER.RECID = dbo.CUSTINVOICEJOUR.WORKERSALESTAKER
  ) as Vendedor
  INTO #TABLAREPORTE
FROM
 dbo.CUSTINVOICETRANS
  INNER JOIN dbo.CUSTINVOICEJOUR ON (dbo.CUSTINVOICETRANS.SALESID = dbo.CUSTINVOICEJOUR.SALESID)
  AND (dbo.CUSTINVOICETRANS.INVOICEID = dbo.CUSTINVOICEJOUR.INVOICEID)
  AND (dbo.CUSTINVOICETRANS.INVOICEDATE = dbo.CUSTINVOICEJOUR.INVOICEDATE)
  AND (dbo.CUSTINVOICETRANS.NUMBERSEQUENCEGROUP = dbo.CUSTINVOICEJOUR.NUMBERSEQUENCEGROUP)
  AND (dbo.CUSTINVOICETRANS.DATAAREAID = dbo.CUSTINVOICEJOUR.DATAAREAID)
  LEFT OUTER JOIN INVENTTRANSORIGIN ON (dbo.CUSTINVOICETRANS.INVENTTRANSID = INVENTTRANSORIGIN.INVENTTRANSID)
 WHERE
  dbo.CUSTINVOICETRANS.DATAAREAID = 'alph' AND
 -- CUSTINVOICETRANS.INVOICEDATE BETWEEN '01/03/2016' AND '31/03/2016' AND
 CUSTINVOICETRANS.INVOICEDATE BETWEEN @fechaInicial AND @fechaFinal AND
  dbo.CUSTINVOICEJOUR.LEDGERVOUCHER NOT LIKE 'ADSICC%'
 -- AND dbo.CUSTINVOICETRANS.INVOICEID = 'F_001002000001871';

  --- Tengo que crear un cursor


  DECLARE cursor_reporte CURSOR FOR
        SELECT
        NoDoc,
        CodItem,
        Diario,
        Cantidad,
        RECID
        FROM #TABLAREPORTE;


  OPEN cursor_reporte
	FETCH NEXT FROM cursor_reporte INTO @INVOICEID,@ITEMID,@LEDGERVOUCHER,@QTY,@RECID
  WHILE @@fetch_status = 0

  	BEGIN


     INSERT INTO #TemporalInventtrans
    Select
   SUM(inventtrans.COSTAMOUNTPOSTED),
   SUM(inventtrans.COSTAMOUNTADJUSTMENT)
   FROM
          inventtrans
        WHERE
          INVENTTRANS.INVOICEID = @INVOICEID AND
          INVENTTRANS.ITEMID=@ITEMID AND
          INVENTTRANS.DATAAREAID = 'alph' AND
          INVENTTRANS.VOUCHER= @LEDGERVOUCHER AND
          INVENTTRANS.INVENTTRANSORIGIN=@RECID
  GROUP BY
  inventtrans.ITEMID;

        SELECT
         --@COSTO= INVENTTRANS.COSTAMOUNTSETTLED
         @COSTO= (TMP1.COSTAMOUNTPOSTED+TMP1.COSTAMOUNTADJUSTMENT)
        FROM
          #TemporalInventtrans TMP1;

        --  PRINT @ITEMID+'-'+@INVOICEID+'-'+@LEDGERVOUCHER+'-'+convert(varchar,@QTY);


          UPDATE #TABLAREPORTE
          SET costo=@COSTO
          Where
           NoDoc = @INVOICEID AND
           Diario= @LEDGERVOUCHER AND
           Cantidad=@QTY AND
           CodItem=@ITEMID;

        SET @COSTO=0;
        Delete from #TemporalInventtrans;

        FETCH NEXT FROM cursor_reporte INTO @INVOICEID,@ITEMID,@LEDGERVOUCHER,@QTY,@RECID
    END

CLOSE cursor_reporte
DEALLOCATE cursor_reporte


INSERT INTO
  #tblReporteVentas(
  NoDoc,
  FechaDoc,
  CodCliente,
  NombCliente,
  CodItem,
  Diario,
  Descripcion,
  Cantidad,
  PrecioUnitario,
  Neto,
  costo,
  vendedor)
Select
 NoDoc,
 FechaDoc,
  NoVenta
 CodCliente,
 NombCliente,
CodItem,
Diario,
 Descripcion,
 Cantidad,
PrecioUnitario,
 Neto,
 costo,
 COALESCE(Vendedor,'') as 'vendedor'
 from #TABLAREPORTE;

Select * from #tblReporteVentas;

/*
Select
CodCliente,
NombCliente,
NoDoc,
SUM(costo) as COSTO
from #TABLAREPORTE
GROUP BY
CodCliente,
NombCliente,
NoDoc
ORDER BY
CodCliente;
*/


DROP TABLE #TABLAREPORTE;
DROP TABLE  #TemporalInventtrans;
DROP TABLE  #tblReporteVentas;

END