package korgeek.util.ext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LimitSizeList<T> extends ArrayList<T>{

	private static final long serialVersionUID = 580658765840589058L;
	private int limit = 1000;
	public LimitSizeList(int size) {
		super(size);
		this.limit = size;
	}

	public static <T> LimitSizeList<T> create(int size){
		return new LimitSizeList<T>(size);
	}
		
	@Override
	public boolean add(T t){
		if(this.size() == limit){
			this.remove(0);
		}
		return super.add(t);
	}

	@Override
	public boolean addAll(Collection<? extends T> coll) {
		Iterator<? extends T> it = coll.iterator();
		boolean rtn = true;
		while (it.hasNext()) {
			rtn = this.add(it.next());
			if(!rtn) break;
		}
		return rtn;
	}
}
