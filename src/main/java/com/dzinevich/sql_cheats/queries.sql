USE sql_store;

select e.employee_id, e.first_name, managers.first_name as manager_name
from employees e
join employees managers
on e.reports_to = managers.employee_id;

select o.order_id, o.order_date, c.first_name, c.last_name, os.name
from orders o
join customers c
	on o.customer_id = c.customer_id
join order_statuses os
	on o.status = os.order_status_id;

USE sql_invoicing;

select p.date, p.amount, c.name as 'client name', p.client_id, pm.name as 'payment'
from payments p
join clients c
	on p.client_id = c.client_id
join payment_methods pm
	on p.payment_method = pm.payment_method_id;

USE sql_store;

-- compound join condition--
select *
from order_items oi
join order_item_notes oin
	on oi.order_id = oin.order_id
    AND oi.product_id = oin.product_id;

USE sql_store;

-- left join means all values from the column on the left (which is customers in our case)
-- because we do 'customenrs left join orders'
-- regadleass of the 'on' condition just return all cutomers
select
    c.customer_id,
    c.first_name,
    o.order_id
from customers c
         left join orders o
                   on c.customer_id = o.customer_id
order by c.customer_id;

-- right join means all values from the column on the right (which is orders in our case)
-- regardless of the 'on' condition just return all orders
-- we'll see all the orders not all the customers
select
    c.customer_id,
    c.first_name,
    o.order_id
from customers c
         right join orders o
                    on c.customer_id = o.customer_id
order by c.customer_id;

-- join product with order_items

select p.product_id, p.name, oi.quantity
from order_items oi
         right join products p
                    on p.product_id = oi.product_id;


-- join customers with orders and shippers table
-- we get all the customers whether they have orders or not,
-- and for those who do have an order we get a shipper whether it has been shipped or not
select
    c.customer_id,
    c.first_name,
    o.order_id,
    sh.name as 'shipper_name'
from customers c
         left join orders o
                   on c.customer_id = o.customer_id
         left join shippers sh
                   on o.shipper_id = sh.shipper_id
order by c.customer_id;

-- avoid using right joind and use left joins in the order

select
    o.order_date,
    o.order_id,
    c.first_name,
    sh.name as 'shipper_name',
        os.name as 'status'
from orders o
         -- here's join or left join it doesn't matter
-- because every order has a cutomer
         join customers c
              on o.customer_id = c.customer_id
-- here's left join because we want to see all orderw whether they have been shipped or not
         left join shippers sh
                   on o.shipper_id = sh.shipper_id
         join order_statuses os
              on o.status = os.order_status_id;
--
--
-- self outer joins

USE sql_hr;
select
    e.employee_id,
    e.first_name,
    m.first_name as 'manager'
from employees e
         left join employees m
                   on e.reports_to = m.employee_id;

-- if the column name is equal in both tables we can replace it with ON(colname)
use sql_store;
select
    o.order_id,
    c.first_name,
    sh.name as 'shipper'
from orders o
         join customers c
              using (customer_id)
         left join shippers sh
                   using (shipper_id);

select *
from order_items oi
         left join order_item_notes oin
    -- on oi.order_id = oin.order_Id
    -- AND oi.product_id = oin.product_id
                   using(order_id, product_id);

use sql_invoicing;
select
    p.date,
    c.name as 'client',
        p.amount,
    pm.name
from payments p
         join clients c
              using (client_id)
         join payment_methods pm
              on p.payment_method = pm.payment_method_id;

--
-- unions
--
use sql_store;

select
    o.order_id,
    o.order_date,
    'Active' as 'status'
from orders o
where o.order_date >= '2018-01-01'
UNION
select
    o.order_id,
    o.order_date,
    'Archive' as 'status'
from orders o
where o.order_date <= '2018-01-01';

-- union in the same table!
select
    c.customer_id,
    c.first_name,
    c.points,
    'Gold' AS rating
from customers c
where points between 2000 and 3000
union
select
    c.customer_id,
    c.first_name,
    c.points,
    'Bronse' AS rating
from customers c
where points between 1000 and 2000;

insert into customers (
-- we can optionally supply a list of columns we want to insert values
    first_name,
    last_name,
    birth_date,
    address,
    city,
    state)
-- PK is auto-incremest so it's better to not have an explicit value but rather have a default generated new value
values (
        'John',
       'Smith',
       '1990-01-01',
        'address',
       'city',
       'CA');

--
-- multiple rows

insert into shippers
    (name)
values
    ('Shipper1'),
    ('Shipper2');

--
-- multiple tables
-- order is a parent for order_items
-- one order may have multiple order_items
insert into orders
(customer_id, order_date, status)
values
    (1, '2021-01-02', 1);
insert into order_items
values (LAST_INSERT_ID(), 1, 1, 2.95),
       (LAST_INSERT_ID(), 2, 2, 4.95);

--
-- copy table without column attributes (auto increment and non-null and defaults are missing in the new table)
create table orders_archived as
select * from orders;

insert into orders_archived
select * from orders
where order_date < '2019-01-01';

-- use select as a subquery to create a table

use sql_invoicing;
create table invoices_archived as
select
    i.invoice_id,
    i.number,
    c.name as client,
    i.invoice_total,
    i.payment_total,
    i.invoice_date,
    i.due_date,
    i.payment_date
from invoices i
         join clients c
              using (client_id)
where i.payment_date is not null;

use sql_invoicing;
update invoices
set
    payment_total = 111,
    payment_date = '2019-03-01'
where invoice_id = '1';

update invoices
set
    payment_total = DEFAULT,
    payment_date = NULL
where invoice_id = '1';

update invoices
set
    payment_total = invoice_total * 0.5,
    payment_date = due_date
where invoice_id = 3;

--
-- update multiple rows

update invoices
set
    payment_total = invoice_total * 0.5,
    payment_date = due_date
where client_id = 3;

use sql_store;
update customers
set points = points + 50
where birth_date > '1990-01-01';

--
-- use subqueries to update

use sql_invoicing;
update invoices
set payment_total = invoice_total + 5.55
where client_id in
      (select client_id
       from clients c
            -- where c.name like '%o%');
       where c.state in ('CA', 'NY'));

--
-- use subqueries to delete
delete from invoices i
where i.client_id =
      (select client_id
       from clients
       where name = 'Myworks');
