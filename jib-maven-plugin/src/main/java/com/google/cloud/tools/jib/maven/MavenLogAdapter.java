package com.google.cloud.tools.jib.maven;

import com.google.cloud.tools.jib.api.LogEvent;
import com.google.cloud.tools.jib.plugins.extension.ExtensionLogger;
import java.util.function.Consumer;

class MavenLogAdapter implements ExtensionLogger {

  private final Consumer<LogEvent> logger;

  MavenLogAdapter(Consumer<LogEvent> logger) {
    this.logger = logger;
  }

  @Override
  public void log(ExtensionLogger.LogLevel logLevel, String message) {
    switch (logLevel) {
      case ERROR:
        logger.accept(LogEvent.error(message));
        break;
      case WARN:
        logger.accept(LogEvent.warn(message));
        break;
      case LIFECYCLE:
        logger.accept(LogEvent.lifecycle(message));
        break;
      case INFO:
        logger.accept(LogEvent.info(message));
        break;
      case DEBUG:
        logger.accept(LogEvent.debug(message));
        break;
      default:
        throw new RuntimeException();
    }
  }
}
