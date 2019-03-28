package hello;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The Start Process transfers information from client to the server during JobsController.StartJobs custom action.
 */
@ApiModel(description = "The Start Process transfers information from client to the server during JobsController.StartJobs custom action.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-28T08:23:46.742Z")

public class StartProcessDto   {
  @JsonProperty("ReleaseKey")
  private String releaseKey = null;

  /**
   * States which robots from the environment are being run by the process.
   */
  public enum StrategyEnum {
    ALL("All"),
    
    SPECIFIC("Specific"),
    
    ROBOTCOUNT("RobotCount"),
    
    JOBSCOUNT("JobsCount");

    private String value;

    StrategyEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StrategyEnum fromValue(String text) {
      for (StrategyEnum b : StrategyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("Strategy")
  private StrategyEnum strategy = null;

  @JsonProperty("RobotIds")
  @Valid
  private List<Long> robotIds = null;

  @JsonProperty("NoOfRobots")
  private Integer noOfRobots = null;

  @JsonProperty("JobsCount")
  private Integer jobsCount = null;

  /**
   * The Source of the job starting the current process.
   */
  public enum SourceEnum {
    MANUAL("Manual"),
    
    SCHEDULE("Schedule");

    private String value;

    SourceEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SourceEnum fromValue(String text) {
      for (SourceEnum b : SourceEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("Source")
  private SourceEnum source = null;

  @JsonProperty("InputArguments")
  private String inputArguments = null;

  public StartProcessDto releaseKey(String releaseKey) {
    this.releaseKey = releaseKey;
    return this;
  }

  /**
   * The unique key of the release associated with the process.
   * @return releaseKey
  **/
  @ApiModelProperty(required = true, value = "The unique key of the release associated with the process.")
  @NotNull


  public String getReleaseKey() {
    return releaseKey;
  }

  public void setReleaseKey(String releaseKey) {
    this.releaseKey = releaseKey;
  }

  public StartProcessDto strategy(StrategyEnum strategy) {
    this.strategy = strategy;
    return this;
  }

  /**
   * States which robots from the environment are being run by the process.
   * @return strategy
  **/
  @ApiModelProperty(value = "States which robots from the environment are being run by the process.")


  public StrategyEnum getStrategy() {
    return strategy;
  }

  public void setStrategy(StrategyEnum strategy) {
    this.strategy = strategy;
  }

  public StartProcessDto robotIds(List<Long> robotIds) {
    this.robotIds = robotIds;
    return this;
  }

  public StartProcessDto addRobotIdsItem(Long robotIdsItem) {
    if (this.robotIds == null) {
      this.robotIds = new ArrayList<Long>();
    }
    this.robotIds.add(robotIdsItem);
    return this;
  }

  /**
   * The collection of ids of specific robots selected to be run by the current process. This collection must be empty only if the start strategy is not Specific.
   * @return robotIds
  **/
  @ApiModelProperty(value = "The collection of ids of specific robots selected to be run by the current process. This collection must be empty only if the start strategy is not Specific.")


  public List<Long> getRobotIds() {
    return robotIds;
  }

  public void setRobotIds(List<Long> robotIds) {
    this.robotIds = robotIds;
  }

  public StartProcessDto noOfRobots(Integer noOfRobots) {
    this.noOfRobots = noOfRobots;
    return this;
  }

  /**
   * DEPRECATED. Number of pending jobs to be created in the environment, for the current process. This number must be greater than 0 only if the start strategy is RobotCount.
   * @return noOfRobots
  **/
  @ApiModelProperty(value = "DEPRECATED. Number of pending jobs to be created in the environment, for the current process. This number must be greater than 0 only if the start strategy is RobotCount.")


  public Integer getNoOfRobots() {
    return noOfRobots;
  }

  public void setNoOfRobots(Integer noOfRobots) {
    this.noOfRobots = noOfRobots;
  }

  public StartProcessDto jobsCount(Integer jobsCount) {
    this.jobsCount = jobsCount;
    return this;
  }

  /**
   * Number of pending jobs to be created in the environment, for the current process. This number must be greater than 0 only if the start strategy is JobsCount.
   * @return jobsCount
  **/
  @ApiModelProperty(value = "Number of pending jobs to be created in the environment, for the current process. This number must be greater than 0 only if the start strategy is JobsCount.")


  public Integer getJobsCount() {
    return jobsCount;
  }

  public void setJobsCount(Integer jobsCount) {
    this.jobsCount = jobsCount;
  }

  public StartProcessDto source(SourceEnum source) {
    this.source = source;
    return this;
  }

  /**
   * The Source of the job starting the current process.
   * @return source
  **/
  @ApiModelProperty(value = "The Source of the job starting the current process.")


  public SourceEnum getSource() {
    return source;
  }

  public void setSource(SourceEnum source) {
    this.source = source;
  }

  public StartProcessDto inputArguments(String inputArguments) {
    this.inputArguments = inputArguments;
    return this;
  }

  /**
   * Input parameters in JSON format to be passed to job execution.
   * @return inputArguments
  **/
  @ApiModelProperty(value = "Input parameters in JSON format to be passed to job execution.")


  public String getInputArguments() {
    return inputArguments;
  }

  public void setInputArguments(String inputArguments) {
    this.inputArguments = inputArguments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartProcessDto startProcessDto = (StartProcessDto) o;
    return Objects.equals(this.releaseKey, startProcessDto.releaseKey) &&
        Objects.equals(this.strategy, startProcessDto.strategy) &&
        Objects.equals(this.robotIds, startProcessDto.robotIds) &&
        Objects.equals(this.noOfRobots, startProcessDto.noOfRobots) &&
        Objects.equals(this.jobsCount, startProcessDto.jobsCount) &&
        Objects.equals(this.source, startProcessDto.source) &&
        Objects.equals(this.inputArguments, startProcessDto.inputArguments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(releaseKey, strategy, robotIds, noOfRobots, jobsCount, source, inputArguments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StartProcessDto {\n");
    
    sb.append("    releaseKey: ").append(toIndentedString(releaseKey)).append("\n");
    sb.append("    strategy: ").append(toIndentedString(strategy)).append("\n");
    sb.append("    robotIds: ").append(toIndentedString(robotIds)).append("\n");
    sb.append("    noOfRobots: ").append(toIndentedString(noOfRobots)).append("\n");
    sb.append("    jobsCount: ").append(toIndentedString(jobsCount)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    inputArguments: ").append(toIndentedString(inputArguments)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

