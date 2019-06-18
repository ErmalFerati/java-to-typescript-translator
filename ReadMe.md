# Java to typescript translator

A simple application made to translate Java classes to Typescript using **Reflection** and **Java Annotations**.

Several examples of annotations are included, such as:

 - TypescriptClass
 - Import
 - Stringified
 - Numerical
 - CustomField

## TypescriptClass
Marks a class to be scanned by the processor and get it translated. Having a constructor is optional but it generates one by default.

## Import
Marks a field as an import so it gets translated as a typescript import statement.

## Stringified
Marks a field as a string type.

## Numerical
Marks a field as a numerical type. By default 6 types are supported (both as objects and primitives):
 - short
 - int
 - integer
 - double
 - long
 - float

## CustomField
Marks a field as a custom field and can be named by passing a parameter to the annotation.


# Example

### User.java

    @TypescriptClass  
    public class User {
    
	    @Numerical
        private BigInteger id;
        private String username;
	   
        @Import
        private UserDetails details;
  
        @CustomField(name = "any")
        private Date birthday;
    }
    
### UserDetails.java
  
	@TypescriptClass(withConstructor = false)
	public class UserDetails {
        private String address;
        private double rating;

        @Stringified
        private char favoriteSymbol;
    }
will generate

### User.ts
    import {UserDetails} from "./UserDetails";
    
    export class User {
    
      public id: number;
      public username: string;
      public details: UserDetails;
      public birthday: any;
    
      public constructor() {
      }
      
    }

### UserDetails.ts
    export class UserDetails {
    
      public address: string;
      public rating: number;
      public favoriteSymbol: string;
      
    }
