# Study for Spring MVC

Inflearn study project

--------------------------------------------

### 1. What is mvc pattern?

The MVC pattern is a methodology that separates the controller and the view, and transmits the information required for the parameters existing in the HttpServletRequest's parameters as an object called the model.

--------------------------------------------

### 2. How to implement Spring MVC structure

The rough implementation of Spring is implemented by actively utilizing polymorphism, and the structure of Spring mvc is roughly composed of DispatcherServlet, HandlerMapping, HandlerAdapter Mapping, ViewResolver, and View.

- DispatcherServlet

It inherits HttpServlet and performs the role of FrontController. Find the corresponding handler in HandlerMapping, find the handler adapter with the found handler,
Handle(handler) is executed through the adapter. The handle returns the ModelAndView object, passes the ModelAndView to the ViewResolver, returns the View, calls the render of the View, and is forwarded to the View in the MVC structure. 

- HandlerMapping

Receives HttpServletRequest and HttpServletResponse, checks the URI in the request information, and finds a handler (controller) in the list of registered handlers.
In other words, it finds handlers (controllers) registered with annotation-based or spring bean names such as @RequestMapping mapped to URIs and returns them to DispatcherServlet.

- HandlerAdapterMapping

This can check whether the object is an instance of an interface through the supports method.
In addition, it is the subject that handles the logic executed by the Handler, and plays a role of using information from the ModelAndView object or containing new information when handling.  

- ViewResolver

In general, based on information in application.properties, ("spring.mvc.view.prefix=/WEB-INF/views/, spring.mvc.view.suffix=.jsp", ... etc) logical address Change to physical address.
For example ("new-form" -> "/WEB-INF/views/new-form/.jsp")

- View

It means a view that is directly delivered to the user, such as JSP or Thymeleaf.

- ModelAndView Object

Directly handling HttpServletRequest, Response, etc. is inefficient, so use ModelAndView object to ensure that only necessary information is delivered.

- View Object

It has a method called render, and when the method is executed, the information of the ModelAndView object is rendered as a request and forwarded to the view.

--------------------------------------------

### 3. Spring MVC Scenario

- Assumption

Assume that controllers A, B, and C are implemented by inheriting the Controller interface, and controllers D and E are implemented by inheriting the HttpRequestHandler interface.
Let X be the adapter that handles the implementation that inherits the Controller interface, and let Y be the adapter that handles the implementation that inherits the HttpRequestHandler interface.
X returns a ModelAndView object, and Y returns void.
Adapter X supports objects A, B, and C, and adapter Y supports objects D and E.

- Sequence

The handler mapping information has these A, B, C, D, E, and when a request corresponding to the controller D comes,
DispatcherServlet returns an object named D from handler mapping information.

From the handler adapter mapping information, an adapter named Y corresponding to object D is found, and the handle method of the adapter named Y is executed.

Since the handle method of Y returns void, the DispatcherServlet will create a new ModelAndView object, not including the Model, but only the Path.
View object including Full(Physical) ViewPath is returned by ViewResolver, and forwarding is performed through the render method of View object.

--------------------------------------------