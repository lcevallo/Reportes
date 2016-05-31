SET ANSI_NULLS ON
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE dbo.LC_COMPORTAMIENTOPAGO
AS
BEGIN
 SET NOCOUNT ON;
DECLARE 

@ACCOUNTNUM nvarchar(20), 
@SETTLEAMOUNTMST numeric(32, 16),
@SETTLEAMOUNTCUR numeric(32, 16) ,
@OFFSETTRANSVOUCHER nvarchar(20),
@TRANSRECID  bigint,
@OFFSETRECID bigint,
@TRANSDATE datetime,
@VOUCHER nvarchar(20),
@ID INT,
@TXT nvarchar(60),
@INVOICE nvarchar(20),
@LASTSETTLEVOUCHER nvarchar(20),
@intFlag INT,
@maxrownumber int,
@resultFechaCobrada nvarchar(20),
 @fechas30P date,
 @fechas45P date,
 @fechas60P date,
 @fechas90P date,
 @fechas120P date,
 @fechas150P date;


  IF OBJECT_ID('tempdb..#TMPCXCCOMPORTAMIENTODETALLE') IS NOT NULL
    BEGIN
        DROP TABLE #TMPCXCCOMPORTAMIENTODETALLE
    END

  IF OBJECT_ID('tempdb..#TMPCXCCOMPORTAMIENTOOFFSETRECID') IS NOT NULL
    BEGIN
        DROP TABLE #TMPCXCCOMPORTAMIENTOOFFSETRECID
    END
    
  IF OBJECT_ID('tempdb..#TMPPIVOTEFINAL') IS NOT NULL
    BEGIN
        DROP TABLE #TMPPIVOTEFINAL
    END

  IF OBJECT_ID('tempdb..#TMPFECHASPAGADAS') IS NOT NULL
    BEGIN
        DROP TABLE #TMPFECHASPAGADAS
    END



CREATE TABLE #TMPFECHASPAGADAS
(
ID int primary key IDENTITY(1,1) NOT NULL,
INVOICE nvarchar(20),
VOUCHER nvarchar(20),
TRANSDATE datetime,
OFFSETTRANSVOUCHER nvarchar(20),
SETTLEAMOUNTMST numeric(32, 16),
TXT nvarchar(60),
ROWNUMBER int,
GRUPO nvarchar(20)
)


   
CREATE TABLE #TMPPIVOTEFINAL (
  ID int primary key IDENTITY(1,1) NOT NULL,
  ACCOUNTNUM nvarchar(20) NULL,
  NOMBRE_CLIENTE nvarchar(100),
  INVOICE nvarchar(20) NULL,
  VOUCHER nvarchar(20) NULL,
  AMOUNTCUR numeric(32, 16) NULL,
  DEBE numeric(38, 16) NULL,
  PAGADA varchar(7),
  TRANSDATE datetime,
  [fechas30P] date,
  [fechas30C] date,
  [fechas30DT] integer,
  [fechas45P] date,
  [fechas45C] date,
  [fechas45DT] integer,
  [fechas60P] date,
  [fechas60C] date,
  [fechas60DT] integer,
  [fechas90P] date,
  [fechas90C] date,
  [fechas90DT] integer,
  [fechas120P] date,
  [fechas120C] date,
  [fechas120DT] integer,
  [fechas150P] date,
  [fechas150C] date,
  [fechas150DT] integer,
)


insert into #TMPPIVOTEFINAL(
 ACCOUNTNUM ,
  NOMBRE_CLIENTE ,
  INVOICE ,
  VOUCHER ,
  AMOUNTCUR ,
  DEBE,
  PAGADA ,
  TRANSDATE 
    )
  SELECT 
  dbo.LC_VISTA_CXC_CLOSE_OPEN.ACCOUNTNUM,
  dbo.LC_VISTA_CXC_CLOSE_OPEN.NOMBRECLIENTE,
  dbo.LC_VISTA_CXC_CLOSE_OPEN.INVOICE,
  dbo.LC_VISTA_CXC_CLOSE_OPEN.VOUCHER,
  dbo.LC_VISTA_CXC_CLOSE_OPEN.AMOUNTCUR,
  dbo.LC_VISTA_CXC_CLOSE_OPEN.DEBE,
  dbo.LC_VISTA_CXC_CLOSE_OPEN.PAGADA,
  dbo.LC_VISTA_CXC_CLOSE_OPEN.TRANSDATE
FROM
  dbo.LC_VISTA_CXC_CLOSE_OPEN;


update tmppivot
  SET
   tmppivot.[fechas30P]=vista.[30 dias],
   tmppivot.[fechas45P]=vista.[45 dias],
   tmppivot.[fechas60P]=vista.[60 dias],
   tmppivot.[fechas90P]=vista.[90 dias],
   tmppivot.[fechas120P]=vista.[120 dias],
   tmppivot.[fechas150P]=vista.[150 dias]
  FROM 
  #TMPPIVOTEFINAL tmppivot
  inner join LC_CXC_PIVOTEMULTIVENCIMIETO vista
   on dbo.LC_GetSplitString_CTE(vista.informacion,'||',1) =tmppivot.INVOICE
   AND dbo.LC_GetSplitString_CTE(vista.informacion,'||',2) =tmppivot.VOUCHER;


-- En esta seccion estoy llenando la tabla de los detalles de mis facturas    
  
  SELECT 
  CUSTTRANS.RECID,
  CUSTTRANS.TRANSTYPE,
  CUSTTRANS.ACCOUNTNUM,
  (SELECT VISTA_CANAL_CLIENTE.NOMBRECLIENTE FROM VISTA_CANAL_CLIENTE WHERE VISTA_CANAL_CLIENTE.ACCOUNTNUM = CUSTTRANS.ACCOUNTNUM) AS NOMBRECLIENTE,
  CUSTTRANS.VOUCHER,
  CUSTTRANS.INVOICE,  
  CUSTSETTLEMENT.OFFSETTRANSVOUCHER,
  CUSTSETTLEMENT.SETTLEAMOUNTMST,
  CUSTSETTLEMENT.SETTLEAMOUNTCUR,
  CUSTSETTLEMENT.TRANSDATE,
  CUSTTRANS.TXT,
  (
  Select  CT2.TXT
  from CUSTTRANS CT2 where 
  CT2.ACCOUNTNUM= CUSTTRANS.ACCOUNTNUM
  AND 
  CT2.VOUCHER=CUSTSETTLEMENT.OFFSETTRANSVOUCHER
  AND
  CT2.DATAAREAID=CUSTTRANS.DATAAREAID
  AND
  CT2.SETTLEMENT=1
  ) as txt2
  INTO #TMPCXCCOMPORTAMIENTODETALLE
FROM
  CUSTSETTLEMENT
  INNER JOIN CUSTTRANS ON (CUSTSETTLEMENT.TRANSRECID = CUSTTRANS.RECID)
  AND (CUSTSETTLEMENT.ACCOUNTNUM = CUSTTRANS.ACCOUNTNUM)
  AND (CUSTSETTLEMENT.TRANSCOMPANY = CUSTTRANS.DATAAREAID)
WHERE
--CUSTTRANS.ACCOUNTNUM='C000048' AND
  (CUSTTRANS.TRANSTYPE = '2' OR 
  CUSTTRANS.TRANSTYPE = '8')
  
  
  
  Alter Table #TMPCXCCOMPORTAMIENTODETALLE Add ID Int Identity(1, 1);
  Alter Table #TMPCXCCOMPORTAMIENTODETALLE Add OFFSETRECID bigint;
   
  
  

--Ahora creare un cursor para ir por cada VOUCHER y asi coger el 
--OFFSETRECID
 -- Declare a cursor to create the dynamic SQL statements
DECLARE Index_CXCCOMPORTAMIENTODETALLE CURSOR FAST_FORWARD
FOR SELECT ID,ACCOUNTNUM, INVOICE, VOUCHER, OFFSETTRANSVOUCHER
FROM #TMPCXCCOMPORTAMIENTODETALLE;


-- Open the cursor for reading
OPEN Index_CXCCOMPORTAMIENTODETALLE;
-- Loop through all the tables in the database
FETCH NEXT FROM Index_CXCCOMPORTAMIENTODETALLE
INTO @ID, @ACCOUNTNUM, @INVOICE, @VOUCHER, @OFFSETTRANSVOUCHER;

WHILE @@FETCH_STATUS =0

BEGIN -- Create ALTER INDEX statement to rebuiId inddex

  
 IF exists (Select 1 from  CUSTTRANS CT2  where CT2.ACCOUNTNUM= @ACCOUNTNUM  AND  CT2.VOUCHER=@OFFSETTRANSVOUCHER AND CT2.DATAAREAID='alph')
   BEGIN
   
       Select 
             @OFFSETRECID=CT2.OFFSETRECID             
            from
             CUSTTRANS CT2
              where 
            CT2.ACCOUNTNUM= @ACCOUNTNUM
            AND 
            CT2.VOUCHER=@OFFSETTRANSVOUCHER
             AND
            CT2.DATAAREAID='alph'
           
         Update #TMPCXCCOMPORTAMIENTODETALLE
         SET OFFSETRECID=@OFFSETRECID
         where
         ID=@ID AND
         VOUCHER=@VOUCHER
         AND
         ACCOUNTNUM=@ACCOUNTNUM
         AND
         INVOICE=@INVOICE
  
   
   END

        
FETCH NEXT FROM Index_CXCCOMPORTAMIENTODETALLE
INTO @ID, @ACCOUNTNUM, @INVOICE, @VOUCHER, @OFFSETTRANSVOUCHER;

        END

-- Get the next index

CLOSE Index_CXCCOMPORTAMIENTODETALLE;
DEALLOCATE Index_CXCCOMPORTAMIENTODETALLE;



DECLARE Index_CXCCOMPORTAMIENTODETALLE2 CURSOR FAST_FORWARD
FOR SELECT DISTINCT ACCOUNTNUM, INVOICE,OFFSETTRANSVOUCHER,OFFSETRECID
FROM #TMPCXCCOMPORTAMIENTODETALLE
where OFFSETRECID>0 AND 
OFFSETTRANSVOUCHER LIKE 'ADLCC%' ;


OPEN Index_CXCCOMPORTAMIENTODETALLE2;
-- Loop through all the tables in the database
FETCH NEXT FROM Index_CXCCOMPORTAMIENTODETALLE2
INTO  @ACCOUNTNUM, @INVOICE, @OFFSETTRANSVOUCHER,@OFFSETRECID;

WHILE @@FETCH_STATUS =0
  BEGIN

  IF OBJECT_ID('tempdb..#TMPCXCCOMPORTAMIENTOOFFSETRECID') IS NULL
  BEGIN
                    SELECT 
                    CUSTSETTLEMENT.ACCOUNTNUM,
                    @INVOICE AS INVOICE,
                    @OFFSETTRANSVOUCHER AS OFFSETTRANSVOUCHER,
                    (SELECT custtrans.VOUCHER FROM custtrans WHERE custtrans.RECID = CUSTSETTLEMENT.TRANSRECID ) AS VOUCHER,
                    (SELECT custtrans.TXT FROM custtrans WHERE custtrans.RECID = CUSTSETTLEMENT.TRANSRECID ) AS TXT,
                    CUSTSETTLEMENT.TRANSRECID,
                    CUSTSETTLEMENT.TRANSDATE,
                    CUSTSETTLEMENT.SETTLEAMOUNTMST
                    INTO #TMPCXCCOMPORTAMIENTOOFFSETRECID      
                  FROM
                    CUSTSETTLEMENT
                  WHERE
                    CUSTSETTLEMENT.TRANSRECID = @OFFSETRECID  
                    AND
                    CUSTSETTLEMENT.ACCOUNTNUM=@ACCOUNTNUM
    

  END
  ELSE
        BEGIN
          INSERT INTO #TMPCXCCOMPORTAMIENTOOFFSETRECID 
              (
                ACCOUNTNUM,
                INVOICE,
                OFFSETTRANSVOUCHER,
                VOUCHER,
                TXT,
                TRANSRECID,
                TRANSDATE,
                SETTLEAMOUNTMST         
              )
            SELECT 
             CUSTSETTLEMENT.ACCOUNTNUM,
             @INVOICE AS INVOICE,
             @OFFSETTRANSVOUCHER AS OFFSETTRANSVOUCHER,
             (SELECT  custtrans.VOUCHER FROM  custtrans WHERE  custtrans.RECID = CUSTSETTLEMENT.TRANSRECID ) AS VOUCHER,
             (SELECT custtrans.TXT FROM  custtrans WHERE custtrans.RECID = CUSTSETTLEMENT.TRANSRECID ) AS TXT,  
             CUSTSETTLEMENT.TRANSRECID,
             CUSTSETTLEMENT.TRANSDATE,
             CUSTSETTLEMENT.SETTLEAMOUNTMST 
          FROM
            CUSTSETTLEMENT
          WHERE
            CUSTSETTLEMENT.TRANSRECID = @OFFSETRECID
            AND
            CUSTSETTLEMENT.ACCOUNTNUM=@ACCOUNTNUM      
          

        END
      
FETCH NEXT FROM Index_CXCCOMPORTAMIENTODETALLE2
INTO  @ACCOUNTNUM, @INVOICE, @OFFSETTRANSVOUCHER,@OFFSETRECID;

  END;

CLOSE Index_CXCCOMPORTAMIENTODETALLE2;
DEALLOCATE Index_CXCCOMPORTAMIENTODETALLE2;
  
Alter Table #TMPCXCCOMPORTAMIENTOOFFSETRECID Add ID Int Identity(1, 1);

insert into #TMPFECHASPAGADAS
  (
   INVOICE,
   VOUCHER,
   TRANSDATE,
   OFFSETTRANSVOUCHER,
   SETTLEAMOUNTMST,
   TXT,
   ROWNUMBER
  ) 
  Select 
       tabla1.INVOICE,
       tabla1.VOUCHER,
       tabla1.TRANSDATE,
       tabla1.OFFSETTRANSVOUCHER,
       tabla1.SETTLEAMOUNTMST,
       tabla1.TXT,
       ROW_NUMBER() OVER(PARTITION BY tabla1.INVOICE ORDER BY tabla1.INVOICE) AS RowNumber
       from 
(
Select INVOICE,
       VOUCHER,
       TRANSDATE,
       OFFSETTRANSVOUCHER,
       SETTLEAMOUNTMST,
       txt2 as TXT
From #TMPCXCCOMPORTAMIENTODETALLE
where
OFFSETTRANSVOUCHER like 'ADCC%'
UNION
Select INVOICE,
       VOUCHER,
       TRANSDATE,
       OFFSETTRANSVOUCHER,
       SETTLEAMOUNTMST,
       TXT
From #TMPCXCCOMPORTAMIENTOOFFSETRECID
) as tabla1



--- SECCION de tetris con las fechas de pago
BEGIN
    

  DECLARE Index_CXCCOMPORTAMIENTOFECHASCOBRO CURSOR FAST_FORWARD
  FOR SELECT ACCOUNTNUM, INVOICE,[fechas30P],[fechas45P],[fechas60P],[fechas90P],[fechas120P],[fechas150P]
  FROM #TMPPIVOTEFINAL;


  OPEN Index_CXCCOMPORTAMIENTOFECHASCOBRO;
  -- Loop through all the tables in the database
  FETCH NEXT FROM Index_CXCCOMPORTAMIENTOFECHASCOBRO
  INTO  @ACCOUNTNUM, @INVOICE, @fechas30P,@fechas45P,@fechas60P,@fechas90P,@fechas120P,@fechas150P
  
  WHILE @@FETCH_STATUS =0
      BEGIN

        --TENGO QUE BUSCAR LAS FECHAS DE COBRO DE CADA UNA DE ESTAS FACTURAS
          Select @maxrownumber =max(ROWNUMBER)          
          from #TMPFECHASPAGADAS
          where INVOICE=@INVOICE
          GROUP BY  INVOICE;
  
          SET @intFlag = 1

           WHILE (@intFlag <= @maxrownumber) 
                BEGIN                  

                   Select @TRANSDATE=TRANSDATE 
                   from #TMPFECHASPAGADAS
                   where INVOICE=@INVOICE AND 
                   ROWNUMBER=@intFlag;

                   SET @resultFechaCobrada= dbo.LC_CXC_COMPARAR_FECHA(@TRANSDATE,@fechas30P,@fechas45P,@fechas60P,@fechas90P,@fechas120P,@fechas150P);

                        UPDATE #TMPFECHASPAGADAS
                          SET GRUPO=@resultFechaCobrada
                          where
                          INVOICE=@INVOICE AND
                          ROWNUMBER=@intFlag;
                           

                   SET @intFlag = @intFlag + 1;

                END


                ---Aqui seria bueno llenar la tabla pivoteada despues de las iteraciones

              UPDATE tmpPvt
              SET
              tmpPvt.[fechas30C]=tbl2.fechaCobrada
              FROM #TMPPIVOTEFINAL tmpPvt
              INNER JOIN 
                (
                    Select INVOICE,MAX(TRANSDATE) as fechaCobrada from #TMPFECHASPAGADAS 
                    where 
                    INVOICE=@INVOICE 
                    AND
                    GRUPO='fecha30P'
                     GROUP BY
                    INVOICE
                  )
                tbl2          
              ON tmpPvt.INVOICE=tbl2.INVOICE
              WHERE
              tmpPvt.INVOICE=@INVOICE
             


              UPDATE tmpPvt
              SET
              tmpPvt.[fechas45C]=tbl2.fechaCobrada
              FROM #TMPPIVOTEFINAL tmpPvt
              INNER JOIN 
                (
                    Select INVOICE,MAX(TRANSDATE) as fechaCobrada from #TMPFECHASPAGADAS 
                    where 
                    INVOICE=@INVOICE 
                    AND
                    GRUPO='fecha45P'
                     GROUP BY
                    INVOICE
                  )
                tbl2          
              ON tmpPvt.INVOICE=tbl2.INVOICE
              WHERE
              tmpPvt.INVOICE=@INVOICE


             
             UPDATE tmpPvt
              SET
              tmpPvt.[fechas60C]=tbl2.fechaCobrada
              FROM #TMPPIVOTEFINAL tmpPvt
              INNER JOIN 
                (
                    Select INVOICE,MAX(TRANSDATE) as fechaCobrada from #TMPFECHASPAGADAS 
                    where 
                    INVOICE=@INVOICE 
                    AND
                    GRUPO='fecha60P'
                    GROUP BY
                    INVOICE
                  )
                tbl2          
              ON tmpPvt.INVOICE=tbl2.INVOICE
              WHERE
              tmpPvt.INVOICE=@INVOICE
              


              UPDATE tmpPvt
              SET
              tmpPvt.[fechas90C]=tbl2.fechaCobrada
              FROM #TMPPIVOTEFINAL tmpPvt
              INNER JOIN 
                (
                    Select INVOICE,MAX(TRANSDATE) as fechaCobrada from #TMPFECHASPAGADAS 
                    where 
                    INVOICE=@INVOICE 
                    AND
                    GRUPO='fecha90P'
                     GROUP BY
                    INVOICE
                  )
                tbl2          
              ON tmpPvt.INVOICE=tbl2.INVOICE
              WHERE
              tmpPvt.INVOICE=@INVOICE

             
              UPDATE tmpPvt
              SET
              tmpPvt.[fechas120C]=tbl2.fechaCobrada
              FROM #TMPPIVOTEFINAL tmpPvt
              INNER JOIN 
                (
                    Select INVOICE,MAX(TRANSDATE) as fechaCobrada from #TMPFECHASPAGADAS 
                    where 
                    INVOICE=@INVOICE 
                    AND
                    GRUPO='fecha120P'
                     GROUP BY
                    INVOICE
                  )
                tbl2          
              ON tmpPvt.INVOICE=tbl2.INVOICE
              WHERE
              tmpPvt.INVOICE=@INVOICE


              UPDATE tmpPvt
              SET
              tmpPvt.[fechas150C]=tbl2.fechaCobrada
              FROM #TMPPIVOTEFINAL tmpPvt
              INNER JOIN 
                (
                    Select INVOICE,MAX(TRANSDATE) as fechaCobrada from #TMPFECHASPAGADAS 
                    where 
                    INVOICE=@INVOICE 
                    AND
                    GRUPO='fecha150P'
                     GROUP BY
                    INVOICE
                  )
                tbl2          
              ON tmpPvt.INVOICE=tbl2.INVOICE
              WHERE
              tmpPvt.INVOICE=@INVOICE

              /*
              UPDATE tmpPvt
              SET
              tmpPvt.[fechas30C]=MAX(tmpFechas.TRANSDATE)
              FROM #TMPPIVOTEFINAL tmpPvt
              INNER JOIN #TMPFECHASPAGADAS tmpFechas
              ON tmpFechas.INVOICE=tmpPvt.INVOICE
              WHERE
              tmpPvt.INVOICE=@INVOICE
              AND
              tmpFechas.GRUPO='fecha150P'
              */



  FETCH NEXT FROM Index_CXCCOMPORTAMIENTOFECHASCOBRO
  INTO  @ACCOUNTNUM, @INVOICE, @fechas30P,@fechas45P,@fechas60P,@fechas90P,@fechas120P,@fechas150P

      END

CLOSE Index_CXCCOMPORTAMIENTOFECHASCOBRO;
DEALLOCATE Index_CXCCOMPORTAMIENTOFECHASCOBRO;

END
--- FIN DE SECCION de tetris con las fechas de pago


UPDATE #TMPPIVOTEFINAL
  SET
  [fechas30DT] =DATEDIFF (day ,[fechas30P],[fechas30C]),
  [fechas45DT] =DATEDIFF (day ,[fechas45P],[fechas45C]),
  [fechas60DT] =DATEDIFF (day ,[fechas60P],[fechas60C]),
  [fechas90DT] =DATEDIFF (day ,[fechas90P],[fechas90C]), 
  [fechas120DT] =DATEDIFF (day ,[fechas120P],[fechas120C]),
  [fechas150DT] =DATEDIFF (day ,[fechas150P],[fechas150C])
 where
  ([fechas30P] is not null AND [fechas30C] is not null)
  OR
  ([fechas45P] is not null AND [fechas45C] is not null)
   OR
  ([fechas60P] is not null AND [fechas60C] is not null)
  OR  
  ([fechas90P] is not null AND [fechas90C] is not null)
   OR
  ([fechas120P] is not null AND [fechas120C] is not null)
   OR
  ([fechas150P] is not null AND [fechas150C] is not null)

/*
Select * from  #TMPCXCCOMPORTAMIENTODETALLE;
Select * from #TMPCXCCOMPORTAMIENTOOFFSETRECID;
Select * from #TMPFECHASPAGADAS;
*/

Select * from #TMPPIVOTEFINAL;





    IF OBJECT_ID('tempdb..#TMPCXCCOMPORTAMIENTODETALLE') IS NOT NULL
    BEGIN
        DROP TABLE #TMPCXCCOMPORTAMIENTODETALLE
    END
    
    
      IF OBJECT_ID('tempdb..#TMPCXCCOMPORTAMIENTOOFFSETRECID') IS NOT NULL
    BEGIN
        DROP TABLE #TMPCXCCOMPORTAMIENTOOFFSETRECID
    END
    
    IF OBJECT_ID('tempdb..#TMPPIVOTEFINAL') IS NOT NULL
      BEGIN
          DROP TABLE #TMPPIVOTEFINAL
      END

   IF OBJECT_ID('tempdb..#TMPFECHASPAGADAS') IS NOT NULL
      BEGIN
          DROP TABLE #TMPFECHASPAGADAS
      END

    END