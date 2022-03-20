package edu.hitsz.props;

public class BloodProps extends AbstractProps{

    private int treatment = 40;

    public  BloodProps (int locationX, int locationY ,int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public int getTreatment() {
        return this.treatment;
    }
}
