package rusketh.Milk.Command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MilkCommand
{
	/*
	 * All the possible names of the command!
	 */
	String[] names();
	
	/*
	 * An example of how to use the command
	 * like: !tp <Player> <Player*>
	 */
	String example() default "";
	
	/*
	 * A brief description of what this command does!
	 */
	String desc() default "";
	
	
	/*
	 * The least amount of arguments
	 */
	int least() default 0;
	
	/*
	 * The most amount of arguments
	 */
	int most() default -1;
	
	/*
	 * Can players use this command!
	 */
	boolean player() default true;
	
	/*
	 * Can console use this command!
	 */
	boolean console() default true;
	
	
	/*
	 * All the permissions needed to use this!
	 */
	String[] perms();
	
}