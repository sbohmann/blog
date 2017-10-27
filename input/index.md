# A Modern Interpretation of OOP

## Objects are about behavior
* OOP is about objects
* Objects are named in a terrifyingly misleading way
* Objects are about behavior
* Objects do not model real-world objects or concepts
* Data structures like structs, POJOs / POCOs, or named tuples are not objects
* Abstract data structures like lists, hash maps, persistent vectors, observables, or other monads have behavior but are semantically still not considered objects

## Objects Are Generalizations of Functions
* Objects have one responsibility
* One responsibility may correspond to more than one function for offering different call signatures
* Stateful objects are created for a single call
* Stateless objects are created for mutliple calls
* Stateful entity objects are created for mutliple calls, which often have effects on the object's state
* Objects are often configured before the first call
* Objects often delegate work to other objects
* Configuring objects with other objects often creates better cohesion than configuring them with data

## Objects Allow Internal Organization
* A Method Object is simply a function written as a class with a single static non-private method
* The method first creates an instance, configured with the arguments and set up with the initial state, and then delegates to a private, non-static method
* Because the configuration and state are visible to all methods, it's now easy to split the code of the original function into several methods, which can vastly improve readability and maintaintability
* This was the first mention of a class, which is not accidental
* Objects are not about classes, inheritance, or runtime polymorphism
* Classes, inheritance, and runtime polymorphism are features of programming languages
* Objects are a more abstract concept that does not rely on any of these features
* Some degree of support helps, though - some language are called "object oriented" for a reason
* LISP is flexible enough to allow OOP without explicit support
* Classic JavaScript allows OOP without supporting classes
* A lot of C code is written in an OOP fashion, which is not pretty
* A lot of C++ code is written in an OOP fashion, which is not much prettier

## Readability and Maintainability
* Bad OOP support in a language and bad tool support often lead to long methods because creating new methods is a lot of distracting extra work
* A good average method length is in between 2 and 3 lines of code
* A good upper limit for class length is about 300 lines, which is actually a lot
* Breaking up methods and classes into more and smaller methods and classes requires good naming skills
* practice is the only way to aquire good naming skills
* When people say, wither all objects always, or no objects ever, should be named like verbs or nouns or abstract concepts, they lack naming skills and attempt to cheat
* There is no cheat sheet for good names
* Consistency helps, though
* The suffix "Manager" is indeed usually an attempt to weasel out of naming
* The suffix "Manager" also often indicates that a class is a kitchen sink full of data with methods around it
* A kitchen sink full of data with methods around it is not an object but a dumpster
* A kitchen sink full of data with methods around it is usually very long
* A kitchen sink full of data with methods around it is not necessary bad, but it is definitely not OOP
* OOP is all about readability and maintainability
* A kitchen sink full of data with methods around it is called structured programming with classes
* Structured programming with classes is not inherently bad but this article is about OOP, so the kitchen sink full of data with methods around it will not be mentioned again

## Classes, Inheritance, and Runtime Polymorphism
* Languages with OOP support are either dynamically typed, or offer dynamic dispatch
* OOP is not about dynamic dispatch
* Language support for dynamic dispatch through virtual methods can help, though
* Without language support for dynamic dispatch through virtual methods, an object would need to resort to function pointers, or a switch statement for dynamic dispatch
* Dynamic dispatch is not an OOP feature but it is often necessary for a lot of reasons
* Classes, inheritance, and runtime polymorphism have a lot uses for other things than objects
* Classes, inheritance, and runtime polymorphism are great for writing data structures like structs, abstract data structures like hash sets, abstractions over basic APIs and low-level details like files, input and output, or a binary network or serial port connection
* Classes, inheritance, and runtime polymorphism are also great for writing object
* Objects do not need classes, inheritance, and runtime polymorphism
* Using classes, inheritance, and runtime polymorphism does not implicitly lead to the creation of objects
* They can be a good match though
* That is why languages with classes, inheritance, and runtime polymorphism are often called "object oriented"
* It is simply an exaggeration