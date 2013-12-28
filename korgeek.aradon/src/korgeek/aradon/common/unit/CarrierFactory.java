package korgeek.aradon.common.unit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.ion.radon.repository.RepositoryCentral;

public class CarrierFactory {

	public final static <T extends AbstractCarrier> T create(RepositoryCentral rc, Class<T> clz){
		try {
			Constructor<T> con = clz.getDeclaredConstructor(RepositoryCentral.class) ;
			
			if (!con.isAccessible())
				con.setAccessible(true);
			
			T result = con.newInstance(rc) ;
			return result ;
			
		} catch (SecurityException e) {
			throw new IllegalArgumentException(e) ;
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException(e) ;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e) ;
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e) ;
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e) ;
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException(e) ;
		}
	}
	
}
