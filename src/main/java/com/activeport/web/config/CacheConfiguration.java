package com.activeport.web.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.activeport.web.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.activeport.web.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.activeport.web.domain.User.class.getName());
            createCache(cm, com.activeport.web.domain.Authority.class.getName());
            createCache(cm, com.activeport.web.domain.User.class.getName() + ".authorities");
            createCache(cm, com.activeport.web.domain.Tenant.class.getName());
            createCache(cm, com.activeport.web.domain.UserRole.class.getName());
            createCache(cm, com.activeport.web.domain.UserRole.class.getName() + ".authorities");
            createCache(cm, com.activeport.web.domain.UserProfile.class.getName());
            createCache(cm, com.activeport.web.domain.CentralSwitch.class.getName());
            createCache(cm, com.activeport.web.domain.Organization.class.getName());
            createCache(cm, com.activeport.web.domain.ItemCode.class.getName());
            createCache(cm, com.activeport.web.domain.BillingSystem.class.getName());
            createCache(cm, com.activeport.web.domain.CircuitVlan.class.getName());
            createCache(cm, com.activeport.web.domain.JunoCommandLog.class.getName());
            createCache(cm, com.activeport.web.domain.MarketPlace.class.getName());
            createCache(cm, com.activeport.web.domain.NotificationEvent.class.getName());
            createCache(cm, com.activeport.web.domain.ServiceCommand.class.getName());
            createCache(cm, com.activeport.web.domain.ServiceCommand.class.getName() + ".serviceConfigurations");
            createCache(cm, com.activeport.web.domain.Ntu.class.getName());
            createCache(cm, com.activeport.web.domain.Ntu.class.getName() + ".ports");
            createCache(cm, com.activeport.web.domain.NtuConfig.class.getName());
            createCache(cm, com.activeport.web.domain.NtuPort.class.getName());
            createCache(cm, com.activeport.web.domain.NtuPort.class.getName() + ".userServices");
            createCache(cm, com.activeport.web.domain.NtuType.class.getName());
            createCache(cm, com.activeport.web.domain.NtuType.class.getName() + ".switches");
            createCache(cm, com.activeport.web.domain.NtuType.class.getName() + ".ntus");
            createCache(cm, com.activeport.web.domain.ServiceType.class.getName());
            createCache(cm, com.activeport.web.domain.ServiceType.class.getName() + ".providers");
            createCache(cm, com.activeport.web.domain.ConfigJob.class.getName());
            createCache(cm, com.activeport.web.domain.RateChangeLog.class.getName());
            createCache(cm, com.activeport.web.domain.VntuDownlinkPort.class.getName());
            createCache(cm, com.activeport.web.domain.Partner.class.getName());
            createCache(cm, com.activeport.web.domain.ProviderPort.class.getName());
            createCache(cm, com.activeport.web.domain.Location.class.getName());
            createCache(cm, com.activeport.web.domain.UserService.class.getName());
            createCache(cm, com.activeport.web.domain.RealmIp.class.getName());
            createCache(cm, com.activeport.web.domain.AddressAllocation.class.getName());
            createCache(cm, com.activeport.web.domain.DeviceConfiguration.class.getName());
            createCache(cm, com.activeport.web.domain.TemplateConfiguration.class.getName());
            createCache(cm, com.activeport.web.domain.PolicerSchedule.class.getName());
            createCache(cm, com.activeport.web.domain.PolicerSchedule.class.getName() + ".policerRanges");
            createCache(cm, com.activeport.web.domain.PolicerRange.class.getName());
            createCache(cm, com.activeport.web.domain.PolicerRange.class.getName() + ".policerSchedules");
            createCache(cm, com.activeport.web.domain.ThirdPartyApi.class.getName());
            createCache(cm, com.activeport.web.domain.XeroAccount.class.getName());
            createCache(cm, com.activeport.web.domain.ProviderLog.class.getName());
            createCache(cm, com.activeport.web.domain.ServiceCode.class.getName());
            createCache(cm, com.activeport.web.domain.ServiceConfiguration.class.getName());
            createCache(cm, com.activeport.web.domain.ServiceConfiguration.class.getName() + ".commands");
            createCache(cm, com.activeport.web.domain.ServiceConfiguration.class.getName() + ".tileConfigurations");
            createCache(cm, com.activeport.web.domain.TileConfiguration.class.getName());
            createCache(cm, com.activeport.web.domain.TileConfiguration.class.getName() + ".services");
            createCache(cm, com.activeport.web.domain.ProviderConfiguration.class.getName());
            createCache(cm, com.activeport.web.domain.ProviderConfiguration.class.getName() + ".services");
            createCache(cm, com.activeport.web.domain.TileTenantConfiguration.class.getName());
            createCache(cm, com.activeport.web.domain.EntityManager.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
