package kr.dogfoot.hwplib.object.bodytext.paragraph.text;


import kr.dogfoot.hwplib.object.bodytext.control.ControlType;
import kr.dogfoot.hwplib.object.bodytext.control.ctrlheader.CtrlID;

/**
 * 확장 컨트롤 Character
 *
 * @author neolord
 */
public class HWPCharControlExtend extends HWPChar {
    /**
     * 추가 정보
     */
    private byte[] addition;

    /**
     * 생성자
     */
    public HWPCharControlExtend() {
    }

    /**
     * 글자의 종류을 반환한다.
     *
     * @return 글자의 타입
     */
    @Override
    public HWPCharType getType() {
        return HWPCharType.ControlExtend;
    }

    /**
     * 컨트롤 객체의 Instance Id를 반환한다.
     *
     * @return 컨트롤 객체의 Instance Id
     */
    public String getInstanceId() {
        int bufferIndex = 0;
        boolean insert = false;
        byte[] buf = new byte[addition.length];
        for (int index = addition.length - 1; index >= 0; index--) {
            if (addition[index] != 0) {
                insert = true;
            }

            if (insert == true) {
                buf[bufferIndex++] = addition[index];
            }
        }
        return new String(buf, 0, bufferIndex);
    }

    /**
     * 추가 정보를 반환한다.
     *
     * @return 추가 정보
     */
    public byte[] getAddition() {
        return addition;
    }

    /**
     * 추가 정보를 설정한다.
     *
     * @param addition 추가 정보
     * @throws Exception
     */
    public void setAddition(byte[] addition) throws Exception {
        if (addition.length != 12) {
            throw new Exception("addition's length must be 12");
        }
        this.addition = addition;
    }

    public boolean isSectionDefine() {
        if (getCode() == 0x0002 && hasAddition('s', 'e', 'c', 'd')) {
            return true;
        }
        return false;
    }

    private boolean hasAddition(char byte1, char byte2, char byte3, char byte4) {
        if (addition != null
                && addition[3] == byte1
                && addition[2] == byte2
                && addition[1] == byte3
                && addition[0] == byte4) {
            return true;
        }
        return false;
    }

    public boolean isColumnDefine() {
        if (getCode() == 0x0002 && hasAddition('c', 'o', 'l', 'd')) {
            return true;
        }
        return false;
    }

    public boolean isFieldStart() {
        if (getCode() == 0x0003
                && addition != null) {
            long ctrlID = CtrlID.make((char) addition[3], (char) addition[2], (char) addition[1], (char) addition[0]);
            return ControlType.isField(ctrlID);
        }
        return false;
    }

    public boolean isHyperlinkStart() {
        if (getCode() == 0x0003 && hasAddition('%', 'h', 'l', 'k')) {
            return true;
        }
        return false;
    }

    public boolean isTable() {
        if (getCode() == 0x000b && hasAddition('t', 'b', 'l', ' ')) {
            return true;
        }
        return false;
    }

    public boolean isGSO() {
        if (getCode() == 0x000b && hasAddition('g', 's', 'o', ' ')) {
            return true;
        }
        return false;
    }

    public boolean isEquation() {
        if (getCode() == 0x000b && hasAddition('e', 'q', 'e', 'd')) {
            return true;
        }
        return false;
    }

    public boolean isForm() {
        if (getCode() == 0x000b && hasAddition('f', 'o', 'r', 'm')) {
            return true;
        }
        return false;
    }

    public boolean isHiddenComment() {
        if (getCode() == 0x000f && hasAddition('t', 'c', 'm', 't')) {
            return true;
        }
        return false;
    }

    public boolean isHeader() {
        if (getCode() == 0x0010 && hasAddition('h', 'e', 'a', 'd')) {
            return true;
        }
        return false;
    }

    public boolean isFooter() {
        if (getCode() == 0x0010 && hasAddition('f', 'o', 'o', 't')) {
            return true;
        }
        return false;
    }

    public boolean isFootNote() {
        if (getCode() == 0x11 && hasAddition('f', 'n', ' ', ' ')) {
            return true;
        }
        return false;
    }

    public boolean isEndNote() {
        if (getCode() == 0x11 && hasAddition('e', 'n', ' ', ' ')) {
            return true;
        }
        return false;
    }


    public boolean isAutoNumber() {
        if (getCode() == 0x12 && hasAddition('a', 't', 'n', 'o')) {
            return true;
        }
        return false;
    }

    public boolean isPageHide() {
        if (getCode() == 0x15 && hasAddition('p', 'g', 'h', 'd')) {
            return true;
        }
        return false;
    }

    public boolean isPageOddEvenAdjust() {
        if (getCode() == 0x15 && hasAddition('p', 'g', 'c', 't')) {
            return true;
        }
        return false;
    }

    public boolean isPageNumberPosition() {
        if (getCode() == 0x15 && hasAddition('p', 'g', 'n', 'p')) {
            return true;
        }
        return false;
    }

    public boolean isIndexMark() {
        if (getCode() == 0x0016 && hasAddition('i', 'd', 'x', 'm')) {
            return true;
        }
        return false;
    }

    public boolean isBookmark() {
        if (getCode() == 0x0016 && hasAddition('b', 'o', 'k', 'm')) {
            return true;
        }
        return false;
    }

    public boolean isAdditionalText() {
        if (getCode() == 0x0017 && hasAddition('t', 'd', 'u', 't')) {
            return true;
        }
        return false;
    }


    public boolean isOverlappingLetter() {
        if (getCode() == 0x0017 && hasAddition('t', 'c', 'p', 's')) {
            return true;
        }
        return false;
    }


    public HWPChar clone() {
        HWPCharControlExtend cloned = new HWPCharControlExtend();
        cloned.code = code;

        if (addition != null) {
            cloned.addition = addition.clone();
        } else {
            cloned.addition = null;
        }

        return cloned;
    }

    @Override
    public int getCharSize() {
        return 8;
    }
}