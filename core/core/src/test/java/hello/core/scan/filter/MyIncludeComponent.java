package hello.core.scan.filter;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
