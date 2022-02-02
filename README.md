#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* WebServerLauncher 클래스를 실행시켜 톰캣 서버를 시작한다.
* 웹 애플리케이션이 실행되면 ServletContextListener 인터페이스를 구현하는 ContextLoaderListener 클래스가 생성된다.
* 생성된 ContextLoaderListener가 ApplicationContext를 로딩한다. ApplicationContext를 로딩하기 위해 클래스패스에 있는 Servlet 인터페이스를 구현하는 서블릿 클래스를 찾는다.
* 톰캣이 실행되면서 'loadOnStartup = 1'이 설정되어 있는 DispatcherServlet 클래스의 init 메소드를 호출해 초기화를 진행한다.
* DispatcherServlet 클래스의 init()에 있는 'rm.initMapping()' 구문에 따라 요청 URL과 서블릿을 연결하는 Map을 생성한다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* @WebServlet의 urlPattern이 '/'인 DispatcherServlet의 service() 메소드가 호출된다.
* 요청 URI를 파라미터로 RequestMapping 클래스의 findController 메소드를 호출하여 요청 URI('/')와 연결된 컨트롤러(HomeController)를 생성한다.
* 조회한 컨트롤러(HomeController)의 execute() 메소드를 호출하여 데이터(질문 목록)과 이동 파일명(home.jsp)이 설정된 View(JspView) 객체를 생성한다.
* 데이터를 파라미터로 View 클래스의 render() 메소드를 호출하여  HttpServletRequest에 데이터를 설정한 뒤, 이동 파일명(home.jsp)으로 이동(Forward)한다.
* HttpServletRequest에 설정한 데이터(질문 목록)가 home.jsp에 바인딩되어 화면에 출력된다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
