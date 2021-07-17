-- 用于测试SQL Server批量提交限制的数据表
create table temp_test_batch_limit
(
	a1 int,
	a2 int,
	a3 int,
	a4 int,
	a5 int,
	a6 int,
	a7 int,
	a8 int,
	a9 int,
	a10 int
)
go
exec sp_addextendedproperty 'MS_Description', '用于测试SQL Server批量提交限制的数据表', 'SCHEMA', 'dbo', 'TABLE', 'temp_test_batch_limit'
go

-- 用于存储测试SQL的数据表
create table temp_test_sql_text
(
	sql_text VARCHAR(MAX)
)
go
exec sp_addextendedproperty 'MS_Description', '用于存储测试SQL的数据表', 'SCHEMA', 'dbo', 'TABLE', 'temp_test_sql_text'
go