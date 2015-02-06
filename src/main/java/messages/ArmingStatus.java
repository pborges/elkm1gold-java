package messages;

import java.util.HashMap;

/**
 * Created by pborges on 1/26/15.
 */
public class ArmingStatus implements Cloneable {
    private Integer area;
    private ARMING_STATUS armingStatus;
    private ARMING_SUB_STATUS armingSubStatus;

    private ALARM_STATE alarmState;
    private ALARM_SUB_STATE alarmSubState;

    private ARM_UP_STATE armUpState;

    public ArmingStatus(){}
    public ArmingStatus(ArmingStatus clone) {
        this.setArea(clone.getArea());
        this.setAlarmState(clone.getAlarmState());
        this.setArmUpState(clone.getArmUpState());
        this.setArmingStatus(clone.getArmingStatus());
        this.setAlarmSubState(clone.getAlarmSubState());
        this.setArmingSubStatus(clone.getArmingSubStatus());
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public ARMING_STATUS getArmingStatus() {
        return armingStatus;
    }

    public void setArmingStatus(Character armingStatus) {
        this.armingStatus = armingStatusHashMap.get(armingStatus);
    }

    public void setArmingStatus(ARMING_STATUS armingStatus) {
        this.armingStatus = armingStatus;
    }

    public ARMING_SUB_STATUS getArmingSubStatus() {
        return armingSubStatus;
    }

    public void setArmingSubStatus(ARMING_SUB_STATUS armingSubStatus) {
        this.armingSubStatus = armingSubStatus;
    }

    public void setArmingSubStatus(Character armingSubStatus) {
        this.armingSubStatus = armingSubStatusHashMap.get(armingSubStatus);
    }

    public ALARM_STATE getAlarmState() {
        return alarmState;
    }

    public void setAlarmState(ALARM_STATE alarmState) {
        this.alarmState = alarmState;
    }

    public void setAlarmState(Character alarmState) {
        this.alarmState = alarmHashMap.get(alarmState);
    }

    public ALARM_SUB_STATE getAlarmSubState() {
        return alarmSubState;
    }

    public void setAlarmSubState(ALARM_SUB_STATE alarmSubState) {
        this.alarmSubState = alarmSubState;
    }

    public void setAlarmSubState(Character alarmSubState) {
        this.alarmSubState = subAlarmHashMap.get(alarmSubState);
    }

    public ARM_UP_STATE getArmUpState() {
        return armUpState;
    }

    public void setArmUpState(Character armUpState) {
        this.armUpState = armUpStateHashMap.get(armUpState);
    }

    public void setArmUpState(ARM_UP_STATE armUpState) {
        this.armUpState = armUpState;
    }


    public static enum ARMING_STATUS {DISARMED, ARMED}

    public static enum ARMING_SUB_STATUS {
        NONE, AWAY, STAY, STAY_INSTANT, NIGHT, NIGHT_INSTANT, VACATION
    }

    public static enum ARM_UP_STATE {
        NOT_READY, READY, READY_BYPASS, ARMED_FULLY, ARMED_EXIT_TIMER, ARMED_FORCED, ARMED_BYPASS
    }

    public static enum ALARM_STATE {
        NO_ALARM, ENTRY_DELAY, ALARM_ABORT_DELAY, FULL_ALARM
    }

    public static enum ALARM_SUB_STATE {
        NONE, FIRE_ALARM, MEDICAL_ALARM, POLICE_ALARM, BURGALAR_ALARM, AUX1_ALARM, AUX2_ALARM, AUX3_ALARM, AUX4_ALARM, CARBON_MONOXIDE_ALARM, EMERGENCY_ALARM, FREEZE_ALARM, GAS_ALARM, HEAT_ALARM, WATER_ALARM, FIRE_SUPERVISORY, VERIFY_FIRE
    }

    private static HashMap<Character, ARMING_STATUS> armingStatusHashMap = new HashMap<Character, ARMING_STATUS>() {{
        put('0', ARMING_STATUS.DISARMED);
        put('1', ARMING_STATUS.ARMED);
        put('2', ARMING_STATUS.ARMED);
        put('3', ARMING_STATUS.ARMED);
        put('4', ARMING_STATUS.ARMED);
        put('5', ARMING_STATUS.ARMED);
        put('6', ARMING_STATUS.ARMED);
    }};
    private static HashMap<Character, ARMING_SUB_STATUS> armingSubStatusHashMap = new HashMap<Character, ARMING_SUB_STATUS>() {{
        put('0', ARMING_SUB_STATUS.NONE);
        put('1', ARMING_SUB_STATUS.AWAY);
        put('2', ARMING_SUB_STATUS.STAY);
        put('3', ARMING_SUB_STATUS.STAY_INSTANT);
        put('4', ARMING_SUB_STATUS.NIGHT);
        put('5', ARMING_SUB_STATUS.NIGHT_INSTANT);
        put('6', ARMING_SUB_STATUS.VACATION);
    }};
    private static HashMap<Character, ARM_UP_STATE> armUpStateHashMap = new HashMap<Character, ARM_UP_STATE>() {{
        put('0', ARM_UP_STATE.NOT_READY);
        put('1', ARM_UP_STATE.READY);
        put('2', ARM_UP_STATE.READY_BYPASS);
        put('3', ARM_UP_STATE.ARMED_EXIT_TIMER);
        put('4', ARM_UP_STATE.ARMED_FULLY);
        put('5', ARM_UP_STATE.ARMED_FORCED);
        put('6', ARM_UP_STATE.ARMED_BYPASS);
    }};
    private static HashMap<Character, ALARM_STATE> alarmHashMap = new HashMap<Character, ALARM_STATE>() {{
        put('0', ALARM_STATE.NO_ALARM);
        put('1', ALARM_STATE.ENTRY_DELAY);
        put('2', ALARM_STATE.ALARM_ABORT_DELAY);
        put('3', ALARM_STATE.FULL_ALARM);
        put('4', ALARM_STATE.FULL_ALARM);
        put('5', ALARM_STATE.FULL_ALARM);
        put('6', ALARM_STATE.FULL_ALARM);
        put('7', ALARM_STATE.FULL_ALARM);
        put('8', ALARM_STATE.FULL_ALARM);
        put('9', ALARM_STATE.FULL_ALARM);
        put(':', ALARM_STATE.FULL_ALARM);
        put(';', ALARM_STATE.FULL_ALARM);
        put('<', ALARM_STATE.FULL_ALARM);
        put('=', ALARM_STATE.FULL_ALARM);
        put('>', ALARM_STATE.FULL_ALARM);
        put('?', ALARM_STATE.FULL_ALARM);
        put('@', ALARM_STATE.FULL_ALARM);
        put('A', ALARM_STATE.FULL_ALARM);
        put('B', ALARM_STATE.FULL_ALARM);
    }};
    private static HashMap<Character, ALARM_SUB_STATE> subAlarmHashMap = new HashMap<Character, ALARM_SUB_STATE>() {{
        put('0', ALARM_SUB_STATE.NONE);
        put('1', ALARM_SUB_STATE.NONE);
        put('2', ALARM_SUB_STATE.NONE);
        put('3', ALARM_SUB_STATE.FIRE_ALARM);
        put('4', ALARM_SUB_STATE.MEDICAL_ALARM);
        put('5', ALARM_SUB_STATE.POLICE_ALARM);
        put('6', ALARM_SUB_STATE.BURGALAR_ALARM);
        put('7', ALARM_SUB_STATE.AUX1_ALARM);
        put('8', ALARM_SUB_STATE.AUX2_ALARM);
        put('9', ALARM_SUB_STATE.AUX3_ALARM);
        put(':', ALARM_SUB_STATE.AUX4_ALARM);
        put(';', ALARM_SUB_STATE.CARBON_MONOXIDE_ALARM);
        put('<', ALARM_SUB_STATE.EMERGENCY_ALARM);
        put('=', ALARM_SUB_STATE.FREEZE_ALARM);
        put('>', ALARM_SUB_STATE.GAS_ALARM);
        put('?', ALARM_SUB_STATE.HEAT_ALARM);
        put('@', ALARM_SUB_STATE.WATER_ALARM);
        put('A', ALARM_SUB_STATE.FIRE_SUPERVISORY);
        put('B', ALARM_SUB_STATE.VERIFY_FIRE);
    }};
}