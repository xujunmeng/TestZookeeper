package 源码实例ZooKeeper客户端的使用一;
import java.io.IOException;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;





public class CreateNodeASync implements Watcher { 



	private static ZooKeeper zookeeper;

	public static void main(String[] args) throws IOException, InterruptedException {

		zookeeper = new ZooKeeper("192.168.1.105:2181",5000,new CreateNodeASync());

		System.out.println(zookeeper.getState());

		

		Thread.sleep(Integer.MAX_VALUE);

	}

	

	private void doSomething(){

	

		zookeeper.create("/node_5", "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,new IStringCallback(),"创建");

			

		

	}

	public void process(WatchedEvent event) {

		// TODO Auto-generated method stub

		System.out.println("收到事件："+event);

		if (event.getState()==KeeperState.SyncConnected){

			if (event.getType()==EventType.None && null==event.getPath()){

				doSomething();

			}

		}

	}

	

	static class IStringCallback implements AsyncCallback.StringCallback{



		public void processResult(int rc, String path, Object ctx, String name) {

			// TODO Auto-generated method stub

			StringBuilder sb = new StringBuilder();

			sb.append("rc="+rc).append("\n");

			sb.append("path="+path).append("\n");

			sb.append("ctx="+ctx).append("\n");

			sb.append("name="+name);

			System.out.println(sb.toString());

			

		}

		

		

	}

	

}


