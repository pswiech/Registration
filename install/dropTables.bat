@echo on

REM run this script from command line

SET DIR="..\db"

SET SQLITE="..\sqlite\Sqlite3.exe"

%SQLITE% %DIR%\Registration.db < dropTables.sql

pause
