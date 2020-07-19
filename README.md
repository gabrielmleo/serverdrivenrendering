
# Server-Driven UI

An app that read  a json specific file that represents screen components and its hierarchy and render native components.

## Behavior

- The application reads a json file and mapper all values to corresponding Widgets objects. 
  - There are two main classes responsible for this proccess, JsonProvider where the json is obtained and JsonHandler that performs the mapping.
- The widgets are encapsulated by ScreenInfo object and send through layers inside a status object called Response.
- MainViewModel class has a Service to handle this specific use case ( get main screen layout )
	  - In case of an error, a default layout is used.   
- A ScreenRenderer class was made to process screenInfo and mapper its value to corresponding android views
- The Activity use this renderer class to get android views and insert them on layout.
  





## Architecture

The code was made to be as simple and straightforward as possible, but not abdicating of some good patterns and practices.
The project doesn't use an specific architecture pattern, instead it combines good practices and elements from multiples 
architectures, methodologies and concepts. Some of them come from :
- [Domain-Driven Design](https://www.amazon.com.br/Domain-Driven-Design-Tackling-Complexity-Software/dp/0321125215)
- [OnionArchitecture](http://jeffreypalermo.com/blog/the-onion-architecture-part-1/) 
- [CleanArchitecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) 
- [Model-View-ViewModel](https://docs.microsoft.com/en-us/archive/msdn-magazine/2009/february/patterns-wpf-apps-with-the-model-view-viewmodel-design-pattern)

The project was divided into five packages:

- **domain**: Contains the application use cases (analogous to DDD's Application Services and Clean Architecture's Use Case), Response model and Repository. 
- **model**: Anemic Domain Models that represents the domain entities.
- **data**: DataSource definition, Data Providers and Data Handlers.
- **di**: Dependency Injection modules,
- **presentation**: Activities, ViewModels, Renderer, view DTOs, that is, everything related to views.

The overall app architecture is as follows

![Gabriel's Challenger Architecture](.\generalfiles\architecture.png)


## Notes
-	Response class is just a simple sealed class to handle success and failure cases. There are other classes more complex to do this job, like kotlin Result and Either.


## Tests
Tests were made using the Behavior-driven Development approach to ensure the classes were working as expected.
I use JUnit 5 to make test classes and functions more readable, using context of inner classes to create "Given", "When" and "Should" pattern.

:construction: Instrumentation tests 

## Design Rationale

As good as showing why something was made, it's important to show why something was **not** made.

**Why not Clean Architecture?**

Even not explicitly using Clean Architecture this project uses (almost all) *basic* concepts 
that originated the Clean Architecture, with the benefit of not over-engineering the app with 
unnecessary elements/layers and mappings. 

**Why not mutiple modules?**

Modules are useful when you need to expose/export those module independently as let's say a library. 
Breaking an app in multiple modules that only works together just generates a "distribute monolith" 
that is difficult to mantain and navigate. A beter approach is to create a well organized package structure 
that is dividide into "contexts" that can be exported independently if needed.

**Why not more Interfaces to provide a "better" Dependency Inversion?**

Agressively using Interfaces to provide Dependency Inversion can cause an over-abstraction problem where tracking data flow becomes 
exhausting. Interfaces should be used wisely and declaring interfaces is not an excuse for better testing since classes can be mocked.


**Why not Dagger?**

Dagger is unnecessarily complex (even so that [another library](https://dagger.dev/hilt/) was created to make it easier) 
Instead of using it, we opt for Koin, a much simpler DI framework with good Kotlin DSL.

**Why not RxJava?**

The best benefit of RxJava is to have a cleaner way to offload tasks to background. 
Instead of using it, I opt for Kotlin coroutines  which is more lightweight, 
is more easier to understand and also provides a good transformation/map API.cd Docu