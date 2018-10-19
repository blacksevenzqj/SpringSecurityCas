package ass.management.admin.common.excel.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang.StringUtils;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DcdbFkInfoItemAOExport {

    // 序号
    private int index;

    // 编号（一级任务字段）
    private String mbfjdseq;

    // 主办部门（一级任务字段）
    private String zrunitname;

    // 工作目标及内容（一级任务字段）
    private String worktarget;

    // 主要进度安排（反馈Iterm字段）
    private String workCont;

    // 办结期限（反馈Iterm字段：根据一级或二级期限字段设置）
    private String bjqxStr;

    // 主要进度安排1（反馈Iterm字段）
    private String situateDescript;

    // 主要进度安排2（反馈Iterm字段）
    private String troubleAndSolv;

    // 主要进度安排3（反馈Iterm字段）
    private String nextWork;

    // 主要进度安排_总和（反馈Iterm字段）
    private String mainSchedule;

    // 当前进度（反馈Iterm字段）
    private String curProgress;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getMbfjdseq() {
        return mbfjdseq;
    }

    public void setMbfjdseq(String mbfjdseq) {
        this.mbfjdseq = mbfjdseq;
    }

    public String getZrunitname() {
        return zrunitname;
    }

    public void setZrunitname(String zrunitname) {
        this.zrunitname = zrunitname;
    }

    public String getWorktarget() {
        return worktarget;
    }

    public void setWorktarget(String worktarget) {
        this.worktarget = worktarget;
    }

    public String getWorkCont() {
        return workCont;
    }

    public void setWorkCont(String workCont) {
        this.workCont = workCont;
    }

    public String getBjqxStr() {
        return bjqxStr;
    }

    public void setBjqxStr(String bjqxStr) {
        this.bjqxStr = bjqxStr;
    }

    public String getSituateDescript() {
        return situateDescript;
    }

    public void setSituateDescript(String situateDescript) {
        this.situateDescript = situateDescript;
    }

    public String getTroubleAndSolv() {
        return troubleAndSolv;
    }

    public void setTroubleAndSolv(String troubleAndSolv) {
        this.troubleAndSolv = troubleAndSolv;
    }

    public String getNextWork() {
        return nextWork;
    }

    public void setNextWork(String nextWork) {
        this.nextWork = nextWork;
    }

    public String getMainSchedule() {
        if (StringUtils.isBlank(mainSchedule)) {
            String temp = StringUtils.isNotBlank(situateDescript) ? situateDescript + "\n" : "";
            temp += StringUtils.isNotBlank(troubleAndSolv) ? troubleAndSolv + "\n" : "";
            temp += StringUtils.isNotBlank(nextWork) ? nextWork + "\n" : "";
            if (StringUtils.isNotBlank(temp)) {
                temp = temp.substring(0, temp.length() - 1);
            }
            setMainSchedule(temp);
            return temp;
        }
        return mainSchedule;
    }

    public void setMainSchedule(String mainSchedule) {
        this.mainSchedule = mainSchedule;
    }

    public String getCurProgress() {
        return curProgress;
    }

    public void setCurProgress(String curProgress) {
        this.curProgress = curProgress;
    }
}
