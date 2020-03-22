package jsonAction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @Author : gavin
 * @Date : 2019-11-1316:19
 * @DESC : jsonfield配置
 */
public class VO {

    @JSONField(name = "ID")
    public int id;

    @JSONField(name ="birthday", format = "yyyy-MM-dd mm:HH:ss")
    public Date date;

    public VO(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "VO{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }

    public static void main(String[] args) {

        Date date = new Date();

        VO vo = new VO(1001,date);

        System.out.println(JSON.toJSONString(vo));

    }
}
