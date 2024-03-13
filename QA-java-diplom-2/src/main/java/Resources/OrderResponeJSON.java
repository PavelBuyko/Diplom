package Resources;

import java.util.List;

public class OrderResponeJSON {
    private Orders[] orders;
    private String total;
    private String totalToday;

    public OrderResponeJSON(){}

    public Orders[] getOrders() {
        return orders;
    }

    public void setOrders(Orders[] orders) {
        this.orders = orders;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(String totalToday) {
        this.totalToday = totalToday;
    }
}
class Orders{
    private Object ingredients = null;
    private String _id;
    private String status;
    private String number;
    private String createdAt;
    private String updatedAt;

    public Orders(){}

    public Object getIngredients() {
        return ingredients;
    }

    public void setIngredients(Object ingredients) {
        this.ingredients = ingredients;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
