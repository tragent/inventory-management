# Inventory Management
Generic Inventory Management for small and medium size businesses

# Dev Recommendation

Lets do development on the develop branch and only merge with master when changes are stableand ready for release.


## Database configuration
* spring.datasource.url=jdbc:mysql://localhost:3306/{databasename}
* spring.datasource.username={database username}
* spring.datasource.password={database password}
* spring.datasource.data-username={database username}
* spring.datasource.data-password={database password}


#### Main developers: **`Team SmartFinance`**

## What works:

* Create user
* Retrieve a list of users in the system
* Retrieve a users's details
* Update user's information
* Delete a user
* Create customer
* Retrieve a list of customers in the system
* Retrieve a customers's details
* Update customer's information
* Delete a customer

## What doesn't:

* Create supplier
* Retrieve a list of suppliers in the system
* Retrieve a suppliers's details
* Update supplier's information
* Delete a supplier
* Make purchase from a supplier
* View sales receipts
* Make sales to a customer
* Make purchase from a supplier
* Create role
* Retrieve a list of roles in the system
* Retrieve a roles's details
* Enable / disable a role
* Retrieve all permissions for a role
* Create permission
* Retrieve a list of permissions in the system
* Update permission
* Retrieve a permissions's details

## IMPROVEMENTS:

* Unit tests
* Optimization techniques
