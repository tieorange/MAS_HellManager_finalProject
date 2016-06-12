package tieorange.edu.hellmanager.main.HellPets;


import tieorange.edu.hellmanager.main.Torturers.Torturer;

/**
 * Created by tieorange on 22/04/16.
 */
public class HellPet implements IFlying, IJumping {
    private HellPetType hellPetType;

    private String name;
    private HellPetColor color;
    private Torturer torturer;

    private Integer flyingSpeed;
    private Integer jumpingSpeed;

    //region Constructor
    public HellPet(String name, HellPetColor color, Torturer torturer, HellPetType type, Integer jumpingSpeed, Integer flyingSpeed) {
        if (type != null) {
            setName(name);
            setColor(color);
            setTorturer(torturer);
            setHellPetType(type);

            if (HellPetType.FLYING.equals(type)) {
                setFlyingSpeed(flyingSpeed);
            } else if (HellPetType.JUMPING.equals(type)) {
                setJumpingSpeed(jumpingSpeed);
            }

        } else {
            throw new IllegalArgumentException("HellPet type is NULL");
        }

        if (HellPetType.FLYING.equals(type)) {
            if (flyingSpeed == null) {
                throw new IllegalArgumentException("flyingSpeed is mandatory for a FLYING HellPet");
            }
        } else if (HellPetType.JUMPING.equals(type)) {
            if (jumpingSpeed == null) {
                throw new IllegalArgumentException("jumpingSpeed is mandatory for a JUMPING HellPet");
            }
        }
    }
    //endregion

    //region Methods

    /**
     * make a pet being flying type
     * @param flyingSpeed flying speed
     */
    public void becomeFlying(Integer flyingSpeed) {
        setHellPetType(HellPetType.FLYING);
        setFlyingSpeed(flyingSpeed);
    }

    /**
     * make a pet being jumping type
     * @param jumpingSpeed jumping speed
     */
    public void becomeJumping(Integer jumpingSpeed) {
        setHellPetType(HellPetType.JUMPING);
        setJumpingSpeed(jumpingSpeed);
    }
    //endregion

    //region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is NULL");
        } else {
            this.name = name;
        }
    }

    public HellPetColor getColor() {
        return color;
    }

    public void setColor(HellPetColor color) {
        if (color == null) {
            throw new IllegalArgumentException("color is NULL");
        } else {
            this.color = color;
        }
    }

    public Torturer getTorturer() {
        return torturer;
    }

    public void setTorturer(Torturer torturer) {
        if (torturer != null) {
            if (this.torturer != torturer) {
                this.torturer = torturer;
                torturer.setHellPet(this);
            }
        } else {
            throw new IllegalArgumentException("torturer is NULL");
        }
    }

    public HellPetType getHellPetType() {
        return hellPetType;
    }

    public void setHellPetType(HellPetType hellPetType) {
        if (hellPetType != null) {
            this.hellPetType = hellPetType;
        } else throw new IllegalArgumentException("hellPetType is NULL");
    }

    //endregion

    //region Getter and Setter from Interface
    @Override
    public void setFlyingSpeed(Integer flyingSpeed) {
        if (HellPetType.FLYING.equals(hellPetType)) {
            if (flyingSpeed != null && flyingSpeed > 0) {
                if (flyingSpeed > 100 && !this.getColor().equals(HellPetColor.RED)) {
                    throw new IllegalArgumentException("Such a high speed (>100) is allowed only for RED color HellPet");
                }
                this.flyingSpeed = flyingSpeed;
            } else throw new IllegalArgumentException("flyingSpeed should be > 0 and NOT NULL");
        } else throw new IllegalArgumentException("HellPet is not a flying pet");
    }

    @Override
    public Integer getFlyingSpeed() {
        return flyingSpeed;
    }

    @Override
    public void fly() {
        if (HellPetType.FLYING.equals(hellPetType)) {
            System.out.println("flying");
        } else throw new RuntimeException("HellPet is not a flying pet");
    }

    @Override
    public void setJumpingSpeed(Integer jumpingSpeed) {
        if (HellPetType.JUMPING.equals(hellPetType)) {
            if (jumpingSpeed != null && jumpingSpeed > 0) {
                this.jumpingSpeed = jumpingSpeed;
            } else throw new IllegalArgumentException("jumpingSpeed should be > 0 and NOT NULL");
        } else throw new IllegalArgumentException("HellPet is not a jumping pet");
    }

    @Override
    public Integer getJumpingSpeed() {
        return jumpingSpeed;
    }

    @Override
    public void jump() {
        if (HellPetType.JUMPING.equals(hellPetType)) {
            System.out.println("jumped");
        } else throw new RuntimeException("HellPet is not a jumping pet");

    }
    //endregion
}
