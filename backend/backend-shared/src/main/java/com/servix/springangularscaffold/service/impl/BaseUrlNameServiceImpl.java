package com.servix.springangularscaffold.service.impl;

import com.servix.springangularscaffold.exception.ApplicationRuntimeException;
import com.servix.springangularscaffold.exception.NotFoundException;
import com.servix.springangularscaffold.repository.BaseUrlNameRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public abstract class BaseUrlNameServiceImpl<T> {
    private final BaseUrlNameRepository<T> repository;

    protected BaseUrlNameServiceImpl(BaseUrlNameRepository<T> repository) {
        this.repository = repository;
    }

    protected String urlNameStrategy(String... args) {
        return urlNameStrategyWithDelimiter("-", args);
    }

    protected String urlNameStrategyWithDelimiter(String delimiter, String... args) {
        if (args.length < 1 && StringUtils.isAnyBlank(args)) {
            throw new ApplicationRuntimeException("No argument is available for the url name generator.");
        }

        String baseUrlName = StringUtils.stripAccents(String.join(delimiter, args));
        baseUrlName = StringUtils.stripAccents(baseUrlName);
        baseUrlName = baseUrlName.replaceAll("[^A-Za-z0-9_.\\- ]", "");
        baseUrlName = StringUtils.normalizeSpace(baseUrlName);
        baseUrlName = StringUtils.trim(baseUrlName);
        baseUrlName = baseUrlName.replaceAll(" ", delimiter);
        baseUrlName = StringUtils.lowerCase(baseUrlName);

        return generateNextValidUrlName(baseUrlName);
    }

    protected T loadByUrlName(String urlName) {
        return repository.findByUrlName(urlName).orElseThrow(
                () -> new NotFoundException("Can not find any entry in " + repository.getClass().getName() + " with urlName=" + urlName));
    }

    private String generateNextValidUrlName(String baseUrlName) {
        Optional<T> optionalByUrlName = repository.findByUrlName(baseUrlName);
        if (optionalByUrlName.isEmpty()) {
            return baseUrlName;
        }
        int index = 0;
        while (true) {
            index++;
            String incrementedBaseUrlName = String.format("%s-%d", baseUrlName, index);
            optionalByUrlName = repository.findByUrlName(incrementedBaseUrlName);
            if (optionalByUrlName.isEmpty()) {
                return incrementedBaseUrlName;
            }
        }
    }
}
