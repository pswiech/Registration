@echo on

REM run this script from command line

SET DIR="..\db"

SET SQLITE="..\sqlite\Sqlite3.exe"

cd %PROJ%

mkdir %DIR%

%SQLITE% %DIR%\Registration.db < createTables.sql

pause
