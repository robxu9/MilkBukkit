package rusketh.Milk.Event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

public @interface MilkEvent {
	/*
	 * What event?
	 */
	String event();
}