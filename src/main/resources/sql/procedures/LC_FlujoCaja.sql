SET ANSI_NULLS ON
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE dbo.LC_FlujoCaja
(
@dia INT,
@mes INT,
@anio INT,
@semanas INT=NULL,
@accountnum VARCHAR(20)=null,
@numsemana INT=null
)
AS
BEGIN


 DECLARE @FechaString VARCHAR(8)=CONVERT(varchar(4), @anio)+RIGHT('00'+ISNULL(CONVERT(varchar(2), @mes),''),2)+RIGHT('00'+ISNULL(CONVERT(varchar(2), @dia),''),2)

 DECLARE  @startDate DATE = CONVERT(DATE,@FechaString,112)


-- SELECT convert(datetime, '20161023', 112) -- ISO yyyymmdd

  DECLARE @i int  =  1;
  --DECLARE @startDate1 DATE=@startDate;
  DECLARE @startDate1 DATE= DATEADD(dd,-2,@startDate)
  DECLARE @endDate1 DATE;



  SET NOCOUNT ON;

/*
IF  NOT EXISTS (SELECT * FROM sys.tables
WHERE name = N'TABLAFLUJO' AND type = 'U')

BEGIN
 CREATE TABLE TABLAFLUJO (
  ID int primary key IDENTITY(1,1) NOT NULL,
  Voucher nvarchar(20)  ,
  ACCOUNTNUM nvarchar(20)  ,
  NOMBRE_CLIENTE nvarchar(100),
  TIPO varchar(22)  ,
  RANGO varchar(21)  ,
  NUMSEMANA int ,
  SEMANA varchar(14)  ,
  UseCashDisc int ,
  dataAreaId nvarchar(4)  ,
  TransDate datetime ,
  DueDate datetime ,
  BankDiscNoticeDeadline datetime ,
  Invoice nvarchar(20) ,
  AmountCur numeric(32, 16) ,
  CurrencyCode nvarchar(3)
)

END
ELSE
BEGIN

  DELETE FROM TABLAFLUJO

END
*/



   IF OBJECT_ID('tempdb..#TABLAFLUJO') IS NOT NULL
    BEGIN
        DROP TABLE #TABLAFLUJO
    END

    IF OBJECT_ID('tempdb..#TEMP_PIVOTE1') IS NOT NULL
    BEGIN
        DROP TABLE #TEMP_PIVOTE1
    END

    IF OBJECT_ID('tempdb..#TEMP_PIVOTE2') IS NOT NULL
    BEGIN
        DROP TABLE #TEMP_PIVOTE2
    END



    IF OBJECT_ID('tempdb..#TABLA_CXCPIVOTE') IS NOT NULL
    BEGIN
        DROP TABLE #TABLA_CXCPIVOTE
    END


    IF OBJECT_ID('tempdb..#SEMANATEMP') IS NOT NULL
    BEGIN
        DROP TABLE #SEMANATEMP
    END



CREATE TABLE #SEMANATEMP
(
ID int primary key NOT NULL,
RANGO varchar(21)
)

CREATE TABLE #TABLAFLUJO (
  ID int primary key IDENTITY(1,1) NOT NULL,
  Voucher nvarchar(20)  ,
  ACCOUNTNUM nvarchar(20)  ,
  NOMBRE_CLIENTE nvarchar(100),
  TIPO varchar(22)  ,
  RANGO varchar(21)  ,
  NUMSEMANA int ,
  SEMANA varchar(14)  ,
  UseCashDisc int ,
  dataAreaId nvarchar(4)  ,
  TransDate datetime ,
  DueDate datetime ,
  BankDiscNoticeDeadline datetime ,
  Invoice nvarchar(20) ,
  AmountCur numeric(32, 16) ,
  CurrencyCode nvarchar(3)
)

 CREATE TABLE #TEMP_PIVOTE1 (
  ID int primary key IDENTITY(1,1) NOT NULL,
  ACCOUNTNUM nvarchar(20) ,
  NOMBRE_CLIENTE nvarchar(100)  ,
  RANGO varchar(21),
  NUMSEMANA int NULL,
  SUMAxSEMANA numeric(38, 16) NULL
)

CREATE TABLE #TEMP_PIVOTE2 (
  ID int primary key IDENTITY(1,1) NOT NULL,
  ACCOUNTNUM nvarchar(20) ,
  [1] numeric(38, 16) NULL,
  [2] numeric(38, 16) NULL,
  [3] numeric(38, 16) NULL,
  [4] numeric(38, 16) NULL,
  [5] numeric(38, 16) NULL,
  [6] numeric(38, 16) NULL,
  [7] numeric(38, 16) NULL,
  [8] numeric(38, 16) NULL
)


CREATE TABLE #TABLA_CXCPIVOTE (
  ID int primary key IDENTITY(1,1) NOT NULL,
  CLIENTE nvarchar(100) NULL,
  CODIGO_CLIENTE nvarchar(100) NOT NULL,
  CREDITMAX numeric(32, 16) DEFAULT 0 NOT NULL,
  UTILIZADO numeric(32, 16) DEFAULT 0 NOT NULL,
  [1] numeric(38, 16) NULL,
  [2] numeric(38, 16) NULL,
  [3] numeric(38, 16) NULL,
  [4] numeric(38, 16) NULL,
  [5] numeric(38, 16) NULL,
  [6] numeric(38, 16) NULL,
  [7] numeric(38, 16) NULL,
  [8] numeric(38, 16) NULL
)


  WHILE @i <=8

  BEGIN

   SET @endDate1 = DATEADD(wk,@i,@startDate)

  INSERT INTO #SEMANATEMP
  (
  ID, RANGO
  ) VALUES
 ( @i,
  LEFT(CONVERT(VARCHAR, @startDate1, 103), 10) + '-' +  LEFT(CONVERT(VARCHAR, @endDate1, 103), 10)
  )



INSERT INTO
  #TABLAFLUJO(
  Voucher,
  ACCOUNTNUM,
  NOMBRE_CLIENTE,
  TIPO,
  RANGO,
  NUMSEMANA,
  SEMANA,
  UseCashDisc,
  dataAreaId,
  TransDate,
  DueDate,
  BankDiscNoticeDeadline,
  Invoice,
  AmountCur,
  CurrencyCode)

  SELECT
  Custtrans.Voucher,
  CusttransOpen.ACCOUNTNUM,
  NOMBRE_CLIENTE=(
      SELECT
        DIRPARTYTABLE.NAME
      FROM
        CUSTTABLE
        LEFT OUTER JOIN DIRPARTYTABLE ON (DIRPARTYTABLE.RECID = CUSTTABLE.PARTY)
      WHERE
        CUSTTABLE.ACCOUNTNUM=CusttransOpen.ACCOUNTNUM
        and
        CUSTTABLE.DATAAREAID=CusttransOpen.DATAAREAID
    ),
  TIPO = CASE
  WHEN (Custtrans.Voucher LIKE 'ADLCC%') THEN 'CHEQUE POSTFECHADO'
  WHEN (Custtrans.Voucher LIKE 'ADNDCL%') THEN 'NOTA DE DEBITO INTERNA'
  ELSE 'FACTURA NO DOCUMENTADA' END,
  RANGO = (SELECT LEFT(CONVERT(VARCHAR, @startDate1, 103), 10) + '-' +  LEFT(CONVERT(VARCHAR, @endDate1, 103), 10)),
  NUMSEMANA=@i,
  SEMANA= CASE
  WHEN @i=1 THEN 'Primera Semana'
  WHEN @i=2 THEN 'Segunda Semana'
  WHEN @i=3 THEN 'Tercera Semana'
  WHEN @i=4 THEN 'Cuarta Semana'
  WHEN @i=5 THEN 'Quinta Semana'
  WHEN @i=6 THEN 'Sexta Semana'
  WHEN @i=7 THEN 'Septima Semana'
  WHEN @i=8 THEN 'Octava Semana'
  ELSE
  '' END,
  CusttransOpen.UseCashDisc,
  Custtrans.dataAreaId,
  CustTrans.TransDate,
  CustTransOpen.DueDate,
  CustTransOpen.BankDiscNoticeDeadline,
  CustTrans.Invoice,
  CustTransOpen.AmountCur,
  CustTrans.CurrencyCode
FROM
  CusttransOpen
  INNER JOIN Custtrans ON (CusttransOpen.ACCOUNTNUM = Custtrans.ACCOUNTNUM)
  AND (CusttransOpen.REFRECID = Custtrans.RECID)
WHERE
  CusttransOpen.DATAAREAID = 'alph'
  AND
  CustTransOpen.DueDate >=    @startDate1 AND
  CustTransOpen.DueDate < @endDate1
  ORDER BY
  NUMSEMANA,
  NOMBRE_CLIENTE

 -- CustTransOpen.DueDate BETWEEN  DATEADD(dd,-2,@startDate1) AND DATEADD(dd,-2,@endDate1)
 -- PRINT LEFT(CONVERT(VARCHAR, @startDate1, 103), 10) + '-' + LEFT(CONVERT(VARCHAR, @endDate1, 103), 10)
   SET @i  =  @i  +  1;
   SET @startDate1 = @endDate1;


  END


IF @semanas IS NOT NULL

	BEGIN

    	Select * from #SEMANATEMP;

    END


ELSE

	BEGIN


		IF (@accountnum IS NULL)
				BEGIN

				INSERT INTO
				  #TEMP_PIVOTE1(
				  ACCOUNTNUM,
				  NOMBRE_CLIENTE,
				  RANGO,
				  NUMSEMANA,
				  SUMAxSEMANA)
				SELECT
				  #TABLAFLUJO.ACCOUNTNUM,
				  #TABLAFLUJO.NOMBRE_CLIENTE,
				  #TABLAFLUJO.RANGO,
				  #TABLAFLUJO.NUMSEMANA,
				  SUM(#TABLAFLUJO.AmountCur) AS SUMAxSEMANA
				FROM
				  #TABLAFLUJO
				GROUP BY
				  #TABLAFLUJO.ACCOUNTNUM,
				  #TABLAFLUJO.NOMBRE_CLIENTE,
				  #TABLAFLUJO.RANGO,
				  #TABLAFLUJO.NUMSEMANA
				ORDER BY
				  #TABLAFLUJO.NUMSEMANA;

				-- Aqui ya tengo los datos pero necesito mostrarlos con mas campos en la
				-- Pagina JSF
				 INSERT INTO  #TEMP_PIVOTE2
					(
					ACCOUNTNUM,
					[1],
					[2] ,
					[3],
					[4],
					[5],
					[6],
					[7],
					[8]
					)
					SELECT * FROM
					(SELECT
					  ACCOUNTNUM,
					  NUMSEMANA ,
					  SUMAxSEMANA
					FROM #TEMP_PIVOTE1

					) as s
					PIVOT
					(
					  SUM(SUMAxSEMANA)
					  FOR NUMSEMANA IN ([1],[2],[3],[4],[5],[6],[7],[8])

					)AS PVT

				   insert into  #TABLA_CXCPIVOTE
				   (
					CODIGO_CLIENTE,
					[1],
					[2] ,
					[3],
					[4],
					[5],
					[6],
					[7],
					[8]
				   )
				   Select
				   ACCOUNTNUM,
				   [1],
					[2] ,
					[3],
					[4],
					[5],
					[6],
					[7],
					[8]
					FROM #TEMP_PIVOTE2


					UPDATE tbcxcpivote
					SET tbcxcpivote.CLIENTE = tablafj.NOMBRE_CLIENTE
					FROM
					#TABLA_CXCPIVOTE tbcxcpivote
					INNER JOIN
					(
					Select DISTINCT ACCOUNTNUM,NOMBRE_CLIENTE
					FROM #TABLAFLUJO
					) AS tablafj
					 ON tbcxcpivote.CODIGO_CLIENTE = tablafj.ACCOUNTNUM


				   UPDATE tbcxcpivote
					SET tbcxcpivote.UTILIZADO = limiteCredito.UTILIZADO,
					 tbcxcpivote.CREDITMAX = limiteCredito.CREDITO
					  FROM #TABLA_CXCPIVOTE tbcxcpivote
					  INNER JOIN JPA_ALPHA_LIMITECREDITO limiteCredito
					  ON tbcxcpivote.CODIGO_CLIENTE = limiteCredito.ACCOUNTNUM


					Select *  from #TABLA_CXCPIVOTE
				END
				ELSE
				BEGIN

					Select * from #TABLAFLUJO
					where ACCOUNTNUM=@accountnum
				   and NUMSEMANA=@numsemana;

				END



    END

    --FIN DEL ELSE




   IF OBJECT_ID('tempdb..#TABLAFLUJO') IS NOT NULL
    BEGIN
        DROP TABLE #TABLAFLUJO
    END

    IF OBJECT_ID('tempdb..#TEMP_PIVOTE1') IS NOT NULL
    BEGIN
        DROP TABLE #TEMP_PIVOTE1
    END

    IF OBJECT_ID('tempdb..#TEMP_PIVOTE2') IS NOT NULL
    BEGIN
        DROP TABLE #TEMP_PIVOTE2
    END



    IF OBJECT_ID('tempdb..#TABLA_CXCPIVOTE') IS NOT NULL
    BEGIN
        DROP TABLE #TABLA_CXCPIVOTE
    END


    IF OBJECT_ID('tempdb..#SEMANATEMP') IS NOT NULL
    BEGIN
        DROP TABLE #SEMANATEMP
    END

END
