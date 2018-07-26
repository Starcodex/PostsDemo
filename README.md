# PostsDemo
Api Consumption Demo https://jsonplaceholder.typicode.com/

How to Build: Clone, Open from Android Studio and update your gradle plugin if is it necessary.

Libraries used:

*Retrofit (Api Rest And Json Convertion) 

*okHttp (Client for http connection) 

*Room (Squlite Database)

*Dagger2 (Dependencies Injection)

for this demo I've decided to use Kotlin Language.

Architecture:

I've used Mvp arquitecture (Model - View - Presenter) with dependencies injection. 
Mvp is a classical 3 layers pattern, Model(Interactor) is the data layer, Presenter is the bussiness logical layer, 
and View is responsible of handle user interactions in view (buttons, etc) the dependencies injection allows a more 
flexible communication betwen the layers, it is perfect for big projects because it avoids repetitive code and it 
facilitates its implementation because each element in app has an unique responsability, and everything are 
components that you can re-use where you need it. it allows to indentify and fix the issues more fast and easy.
