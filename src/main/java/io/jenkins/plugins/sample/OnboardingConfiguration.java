package io.jenkins.plugins.sample;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kohsuke.stapler.QueryParameter;

import hudson.Extension;
import hudson.util.FormValidation;
import jenkins.model.GlobalConfiguration;

@Extension
public class OnboardingConfiguration extends GlobalConfiguration {

    private static final Log LOGGER = LogFactory.getLog(OnboardingConfiguration.class);

    private String name;
    private String description;

    public OnboardingConfiguration(){
        load();
        LOGGER.info("Loaded configuration: name=" + name + ", description=" + description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        save();
        LOGGER.info("Saved name: " + name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FormValidation doCheckName(@QueryParameter String value) throws IOException, ServletException {
        if(value.isEmpty()){
            return FormValidation.warning("value is empty");
        }else{
            Pattern pattern=Pattern.compile("^[A-Za-z\\s]+$");
            Matcher matcher=pattern.matcher(value);
            if(!matcher.matches()){
                return FormValidation.error("Invalid data in value");
            }else{
                return FormValidation.ok("All good!");
            }
        }
    }
}
