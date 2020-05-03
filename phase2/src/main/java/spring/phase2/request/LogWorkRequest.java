package spring.phase2.request;
import java.util.List;

import spring.phase2.entity.Log_Work;

public class LogWorkRequest {
	private List<Log_Work> logs;

	public List<Log_Work> getLogs() {
		return logs;
	}

	public void setLogs(List<Log_Work> logs) {
		this.logs = logs;
	}
}
