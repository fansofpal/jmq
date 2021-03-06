package com.ipd.jmq.common.network.v3.command;

import com.ipd.jmq.common.network.v3.session.ConsumerId;
import com.ipd.jmq.toolkit.lang.Preconditions;

/**
 * 请求消息
 */
public class GetMessage extends JMQPayload {
    // 消费者ID
    protected ConsumerId consumerId;
    // 数量
    protected short count;
    // 长轮询时间（毫秒）
    protected int longPull = 0;
    // 主题
    protected String topic;
    // 消费者应答超时时间(服务端配置)
    protected int ackTimeout;
    // 队列号
    protected short queueId = 0;
    // 偏移量
    protected long offset = -1L;

    public GetMessage count(final short count) {
        setCount(count);
        return this;
    }

    public GetMessage longPull(final int longPull) {
        setLongPull(longPull);
        return this;
    }

    public GetMessage consumerId(final ConsumerId consumerId) {
        setConsumerId(consumerId);
        return this;
    }

    public GetMessage topic(final String topic) {
        setTopic(topic);
        return this;
    }

    public GetMessage ackTimeout(final int ackTimeout) {
        setAckTimeout(ackTimeout);
        return this;
    }

    public GetMessage queueId(final short queueId) {
        setQueueId(queueId);
        return this;
    }

    public GetMessage offset(final long offset) {
        setOffset(offset);
        return this;
    }


    public short getQueueId() {
        return queueId;
    }

    public void setQueueId(short queueId) {
        this.queueId = queueId;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public ConsumerId getConsumerId() {
        return this.consumerId;
    }

    public void setConsumerId(ConsumerId consumerId) {
        this.consumerId = consumerId;
    }

    public short getCount() {
        return this.count;
    }

    public void setCount(short count) {
        this.count = count;
    }

    public int getLongPull() {
        return this.longPull;
    }

    public void setLongPull(int longPull) {
        this.longPull = longPull;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getAckTimeout() {
        return ackTimeout;
    }

    public void setAckTimeout(int ackTimeout) {
        this.ackTimeout = ackTimeout;
    }

    public int predictionSize() {
        return Serializer.getPredictionSize(consumerId.getConsumerId(), topic) + 6 + 10 + 1;
    }

    @Override
    public void validate() {
        super.validate();
        Preconditions.checkArgument(consumerId != null, "consumer ID can not be null.");
        Preconditions.checkArgument(count > -1, "count must bigger than -1.");
        Preconditions.checkArgument(topic != null && !topic.isEmpty(), "topic can not be null.");
    }

    @Override
    public int type() {
        return CmdTypes.GET_MESSAGE;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetMessage{");
        sb.append("consumerId=").append(consumerId);
        sb.append(", count=").append(count);
        sb.append(", longPull=").append(longPull);
        sb.append(", topic='").append(topic).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        GetMessage that = (GetMessage) o;

        if (count != that.count) {
            return false;
        }
        if (longPull != that.longPull) {
            return false;
        }
        if (consumerId != null ? !consumerId.equals(that.consumerId) : that.consumerId != null) {
            return false;
        }
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) {
            return false;
        }
        if (queueId != that.queueId) {
            return false;
        }
        if (offset != that.offset) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (consumerId != null ? consumerId.hashCode() : 0);
        result = 31 * result + (int) count;
        result = 31 * result + longPull;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        return result;
    }
}