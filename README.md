# ddt
## Devious Database Testing

A library to make Database testing a little easier.  It currently comprises:
 * Prepared Statement Spy - this will wrap a prepared statement instance and spy 
   on the setter calls, to allow you to retrieve the SQL with the parameters 
   pre-formatted within the original SQL.  The intent is to allow access to the 
   SQL and params that are quite often obscured by the driver such that the SQL 
   could just be taken and run against the database.
   