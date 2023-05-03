package za.co.wethinkcode.examples.server.robot;

public enum Direction {
    NORTH ("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    private String name;

    Direction(String name) {
        this.name = name;
    }


    @Override
    public String toString()
    {
        return name;
    }



    public static Direction findByName(String name)
    {
        for (Direction code : Direction.class.getEnumConstants())
        {
            if (name.equals(code.toString()))
            {
                return code;
            }
        }
        return null;
    }
}

