package mt.inheritableThreadLocal;

import java.util.Date;

public class Tools {
	
	public static InheritableThreadLocal<Date> t = new InheritableThreadLocal<Date>(){
		@Override
		protected Date initialValue() {
	        return new Date();
	    }
	};
	
}

