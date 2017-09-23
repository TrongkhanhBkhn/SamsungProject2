class GameAction 
{
  // Khao bao cac trang thai An,nha ....
    public static final int NORMAL = 0;

    public static final int DETECT_INITAL_PRESS_ONLY = 1;

    private static final int STATE_RELEASED = 0;
    private static final int STATE_PRESSED = 1;
    private static final int STATE_WAITING_FOR_RELEASE = 2;

    private String name; // Ten
    private int behavior; 
    private int amount;  // So luong
    private int state; // Trang thai

    public GameAction(String name) // Contructor truyen tham so vao la ten
    {
        this(name, NORMAL);
    }


    public GameAction(String name, int behavior) {  // Contructor truyen tham so vao la ten va behavior
        this.behavior = behavior;
        reset();
    }


    public String getName() { // Lay ten
        return name;
    }


    public void reset() {
        state = STATE_RELEASED;
        amount = 0;
    }


    public synchronized void tap() { // Khi Click Tab
        press();
        release();
    }


    public synchronized void press() { // Khi Click
        press(1);
    }


    public synchronized void press(int amount) {
        if (state != STATE_WAITING_FOR_RELEASE) {
            this.amount+=amount;
            state = STATE_PRESSED;
        }

    }


    public synchronized void release() { // Khi Bo Chuot
        state = STATE_RELEASED;
    }


    public synchronized boolean isPressed() {
        return (getAmount() != 0);
    }


    public synchronized int getAmount() {
        int retVal = amount;
        if (retVal != 0) {
            if (state == STATE_RELEASED) {
                amount = 0;
            }
            else if (behavior == DETECT_INITAL_PRESS_ONLY) {
                state = STATE_WAITING_FOR_RELEASE;
                amount = 0;
            }
        }
        return retVal;
    }
}
