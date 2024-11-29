package model;

import javax.swing.*;

public interface IPlayer {
    //    trả về nước đi tiếp theo của người chơi
    //thiết lập lượt chơi
    void claimBox(Box box);

    //mã nhận dạng
    int getToken();

    //điểm
    int getScore();

}
