SET ANSI_NULLS ON
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE dbo.LC_HISTORIAL_PROTESTO
(
@ACCOUNTNUM varchar(20)=NULL,
@mes INTEGER=null
)
AS
BEGIN
   SET NOCOUNT ON;

 IF OBJECT_ID('tempdb..#HISTORIALPROTESTO') IS NOT NULL
    BEGIN
        DROP TABLE #HISTORIALPROTESTO
    END

  IF OBJECT_ID('tempdb..#TMP_PVT_HISTORIAL_PROTESTO_BEFORE') IS NOT NULL
    BEGIN
        DROP TABLE #TMP_PVT_HISTORIAL_PROTESTO_BEFORE
    END

   IF OBJECT_ID('tempdb..#TMP_PVT_HISTORIAL_PROTESTO') IS NOT NULL
    BEGIN
        DROP TABLE #TMP_PVT_HISTORIAL_PROTESTO
    END


    IF OBJECT_ID('tempdb..#TMP_PVT_HISTORIAL_PROTESTO_AFTER') IS NOT NULL
    BEGIN
        DROP TABLE #TMP_PVT_HISTORIAL_PROTESTO_AFTER
    END


CREATE TABLE #HISTORIALPROTESTO (
  ID int primary key IDENTITY(1,1) NOT NULL,
  TRANSDATE datetime NOT NULL,
  OrderMonth INTEGER  NULL,
  ACCOUNTNUM nvarchar(20)  NOT NULL,
  NOMBRE_CLIENTE nvarchar(100)  NULL,
  AmountCurDebit numeric(32, 16) NOT NULL,
  PostingProfile nvarchar(10)  NOT NULL,
  VOUCHER nvarchar(20) NOT NULL
)

CREATE TABLE #TMP_PVT_HISTORIAL_PROTESTO_BEFORE (
  ID int primary key IDENTITY(1,1) NOT NULL,
  cantidad int NULL,
  ACCOUNTNUM nvarchar(20)  NOT NULL,
  OrderMonth INTEGER NULL,
  NOMBRE_CLIENTE nvarchar(100) NULL
)

CREATE TABLE #TMP_PVT_HISTORIAL_PROTESTO_AFTER (
  ID int primary key IDENTITY(1,1) NOT NULL,
  ACCOUNTNUM nvarchar(20) NOT NULL,
  NOMBRE_CLIENTE nvarchar(100),
  [1] int NULL,
  [2] int NULL,
  [3] int NULL,
  [4] int NULL,
  [5] int NULL,
  [6] int NULL,
  [7] int NULL,
  [8] int NULL,
  [9] int NULL,
  [10] int NULL,
  [11] int NULL,
  [12] int NULL
)



INSERT INTO
  #HISTORIALPROTESTO(
  TRANSDATE,
  OrderMonth,
  ACCOUNTNUM,
  NOMBRE_CLIENTE,
  AmountCurDebit,
  PostingProfile,
  VOUCHER)
SELECT
  LedgerJournalTrans.TRANSDATE,
  [OrderMonth]=CONVERT(varchar(3), DATEPART(MONTH,LedgerJournalTrans.TRANSDATE)),
  CustTrans.ACCOUNTNUM,
  NOMBRE_CLIENTE=(
      SELECT
        DIRPARTYTABLE.NAME
      FROM
        CUSTTABLE
        LEFT OUTER JOIN DIRPARTYTABLE ON (DIRPARTYTABLE.RECID = CUSTTABLE.PARTY)
      WHERE
        CUSTTABLE.ACCOUNTNUM=CustTrans.ACCOUNTNUM
        and
        CUSTTABLE.DATAAREAID=CustTrans.DATAAREAID
    ),
  LedgerJournalTrans.AmountCurDebit,
  LedgerJournalTrans.PostingProfile,
  CustTrans.VOUCHER
FROM
  CustTrans
  INNER JOIN LedgerJournalTrans ON (CustTrans.RECID = LedgerJournalTrans.CUSTTRANSID)
  AND (CustTrans.DATAAREAID = LedgerJournalTrans.DATAAREAID)
WHERE
 -- LedgerJournalTrans.TXT LIKE '%PROT%' AND
  LedgerJournalTrans.DATAAREAID = 'alph' AND
  LedgerJournalTrans.PostingProfile = 'CHQ_DEV';


  --PIVOTEO


  INSERT INTO
  #TMP_PVT_HISTORIAL_PROTESTO_BEFORE(
  cantidad,
  ACCOUNTNUM,
  OrderMonth,
  NOMBRE_CLIENTE)
  SELECT
  COUNT(*) AS cantidad,
  #HISTORIALPROTESTO.ACCOUNTNUM,
  #HISTORIALPROTESTO.OrderMonth,
  #HISTORIALPROTESTO.NOMBRE_CLIENTE
    FROM
      #HISTORIALPROTESTO
    GROUP BY
      #HISTORIALPROTESTO.ACCOUNTNUM,
      #HISTORIALPROTESTO.OrderMonth,
      #HISTORIALPROTESTO.NOMBRE_CLIENTE

	IF (@ACCOUNTNUM IS NULL)
    	BEGIN

          SELECT * INTO #TMP_PVT_HISTORIAL_PROTESTO  FROM
              (SELECT
                ACCOUNTNUM,
                OrderMonth ,
                cantidad
              FROM #TMP_PVT_HISTORIAL_PROTESTO_BEFORE

              ) as s
              PIVOT
              (
                SUM(cantidad)
                FOR OrderMonth IN ([1],[2],[3],[4],[5],[6],[7],[8],[9],[10],[11],[12])
              )AS PVT



              INSERT INTO  #TMP_PVT_HISTORIAL_PROTESTO_AFTER(
                  ACCOUNTNUM,
                  NOMBRE_CLIENTE,
                  [1],
                  [2],
                  [3],
                  [4],
                  [5],
                  [6],
                  [7],
                  [8],
                  [9],
                  [10],
                  [11],
                  [12])
               SELECT
                  #TMP_PVT_HISTORIAL_PROTESTO.ACCOUNTNUM,
                 NOMBRE_CLIENTE =(
                 Select TOP 1 NOMBRE_CLIENTE  from #TMP_PVT_HISTORIAL_PROTESTO_BEFORE where #TMP_PVT_HISTORIAL_PROTESTO_BEFORE.ACCOUNTNUM = #TMP_PVT_HISTORIAL_PROTESTO.ACCOUNTNUM
                 ),
                  #TMP_PVT_HISTORIAL_PROTESTO.[1],
                  #TMP_PVT_HISTORIAL_PROTESTO.[2],
                  #TMP_PVT_HISTORIAL_PROTESTO.[3],
                  #TMP_PVT_HISTORIAL_PROTESTO.[4],
                  #TMP_PVT_HISTORIAL_PROTESTO.[5],
                  #TMP_PVT_HISTORIAL_PROTESTO.[6],
                  #TMP_PVT_HISTORIAL_PROTESTO.[7],
                  #TMP_PVT_HISTORIAL_PROTESTO.[8],
                  #TMP_PVT_HISTORIAL_PROTESTO.[9],
                  #TMP_PVT_HISTORIAL_PROTESTO.[10],
                  #TMP_PVT_HISTORIAL_PROTESTO.[11],
                  #TMP_PVT_HISTORIAL_PROTESTO.[12]
                FROM
                  #TMP_PVT_HISTORIAL_PROTESTO


           Select *  from #TMP_PVT_HISTORIAL_PROTESTO_AFTER;

        END



    ELSE
    	BEGIN

        	Select *   from #HISTORIALPROTESTO where ACCOUNTNUM=@ACCOUNTNUM AND OrderMonth=@mes


        END



IF OBJECT_ID('tempdb..#HISTORIALPROTESTO') IS NOT NULL
    BEGIN
        DROP TABLE #HISTORIALPROTESTO
    END

  IF OBJECT_ID('tempdb..#TMP_PVT_HISTORIAL_PROTESTO_BEFORE') IS NOT NULL
    BEGIN
        DROP TABLE #TMP_PVT_HISTORIAL_PROTESTO_BEFORE
    END

   IF OBJECT_ID('tempdb..#TMP_PVT_HISTORIAL_PROTESTO') IS NOT NULL
    BEGIN
        DROP TABLE #TMP_PVT_HISTORIAL_PROTESTO
    END


    IF OBJECT_ID('tempdb..#TMP_PVT_HISTORIAL_PROTESTO_AFTER') IS NOT NULL
    BEGIN
        DROP TABLE #TMP_PVT_HISTORIAL_PROTESTO_AFTER
    END



END
