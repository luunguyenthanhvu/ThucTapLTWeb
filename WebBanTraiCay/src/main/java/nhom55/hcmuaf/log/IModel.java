package nhom55.hcmuaf.log;

public interface IModel {
  String getTable();
  String beforeData(Object model);
  String afterData(Object model);
}
