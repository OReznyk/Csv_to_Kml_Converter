package main.java.views;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import main.java.Tools.ReaderFromCsv;
import main.java.WifiData.ListOfWifiRows;

public class FolderWatcher implements Runnable {
	private static int test;
	private static Map<WatchKey, Path> keyPathMap = new HashMap<>();
	private static Path path;

	public static void main (String[] args) throws Exception {
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			registerDir(Paths.get("d:\\testDir"), watchService);
			startListening(watchService);
		}
	}
	
	@Override
	public void run() {
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			registerDir(path, watchService);
			startListening(watchService);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FolderWatcher(Path path) throws Exception
	{
		FolderWatcher.path = path;
	}

	private static void registerDir (Path path, WatchService watchService) throws
	IOException {


		if (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
			return;
		}

		//System.out.println("registering: " + path);


		WatchKey key = path.register(watchService,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_MODIFY,
				StandardWatchEventKinds.ENTRY_DELETE);
		keyPathMap.put(key, path);


		for (File f : path.toFile().listFiles()) {
			registerDir(f.toPath(), watchService);
		}
	}

	private static void startListening (WatchService watchService) throws Exception {
		String event;
		while (true) {
			WatchKey queuedKey = watchService.take();
			for (WatchEvent<?> watchEvent : queuedKey.pollEvents()) {

				event = "("+watchEvent.kind()+") "+watchEvent.context()+", in folder: "+path.toAbsolutePath().toString();	
				System.out.println(event);
				test=1;
				

				if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					//this is not a complete path
					Path path = (Path) watchEvent.context();
					//need to get parent path
					Path parentPath = keyPathMap.get(queuedKey);
					//get complete path
					path = parentPath.resolve(path);
					registerDir(path, watchService);
				}
				
				
			}
			if(!queuedKey.reset()){
				keyPathMap.remove(queuedKey);
			}
			if(keyPathMap.isEmpty()){
				break;
			}
		}
	}

	public static int getTest() {
		return test;
	}

	public static void setTest(int test) {
		FolderWatcher.test = test;
	}

	
}


