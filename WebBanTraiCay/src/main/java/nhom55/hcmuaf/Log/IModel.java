package nhom55.hcmuaf.Log;

public interface IModel {
  String getTable();
  String beforeData(Object model);
  String afterData(Object model);
}
