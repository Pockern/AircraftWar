package edu.hitsz.propsfactory;

import edu.hitsz.props.AbstractProps;

import java.util.List;

public interface PropsFactory {
    public List<AbstractProps> createProps(int locationX, int locationY, int speedX, int speedY);
}
