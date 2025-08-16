package net.engineeringdigest.journalApp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;

@Component
public class AppCache {

	public Map<String, String> appCache;
	
	public enum keys{
		WEATHER_API;
	}
	
	@Autowired
	private ConfigJournalAppRepository configJournalAppRepository;
	
	@PostConstruct
	public void init()
	{
		appCache = new HashMap<>();
		List<ConfigJournalAppEntity>  all = configJournalAppRepository.findAll();
		for(ConfigJournalAppEntity configJournalAppEntity : all)
		{
			appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
		}
	}
}
