package controllers;

import java.util.*;
import models.*;

import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  // ルートにアクセスした際のAction
  public static Result index() {
    return ok(index.render(
      "ここで渡した値がindex.scala.htmlへ渡される"
    ));
  }
  
  public static Result list(){
    return ok(list.render(

    ));
  }

}
