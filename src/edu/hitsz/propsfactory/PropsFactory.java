package edu.hitsz.propsfactory;

import edu.hitsz.props.AbstractProps;

import java.util.List;

public abstract class PropsFactory {
    public abstract List<AbstractProps> createProps(int locationX, int locationY);
}
