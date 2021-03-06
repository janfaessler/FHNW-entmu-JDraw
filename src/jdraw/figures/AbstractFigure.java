package jdraw.figures;

import java.util.ArrayList;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

public abstract class AbstractFigure implements Figure {

	private static final long serialVersionUID = 2736707033115687659L;
	
	private ArrayList<FigureListener> _figureListenerList = new ArrayList<FigureListener>();
	
	@Override
	public Object clone() {
		try {
			AbstractFigure copy = (AbstractFigure) super.clone();
			copy._figureListenerList = new ArrayList<FigureListener>();
			return copy;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}
	
	@Override
	public void addFigureListener(FigureListener listener) {
		_figureListenerList.add(listener);
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		_figureListenerList.remove(listener);
	}
	
	protected void updateAllListener() {
		FigureListener[] copy = _figureListenerList.toArray(new FigureListener[_figureListenerList.size()]);
		for (FigureListener listener : copy) {
			listener.figureChanged(new FigureEvent(this));
		}
	}
}
