package korgeek.util.ext;

import java.util.HashMap;

public class CaseInsensitiveHashMap<V> extends HashMap<String,V>{

	private static final long serialVersionUID = 5858585858585858L;
	
	@Override
	public boolean containsKey(Object name){
		return super.containsKey(((String)name).toLowerCase());
	}
	
	public boolean containsKey(String name){
		return super.containsKey(name.toLowerCase());
	}
	
	@Override
	public V get(Object name)
	{
		return super.get(((String) name).toLowerCase());
	}

	public V get(String name)
	{
		return super.get(name.toLowerCase());
	}	
	
	@Override
	public V put(String name, V value)
	{
		return super.put(name.toLowerCase(), value);
	}
	   
	@Override
	public V remove(Object name)
	{
		return super.remove(((String) name).toLowerCase());
	}

	public V remove(String name)
	{
		return super.remove(name.toLowerCase());
	}
}
