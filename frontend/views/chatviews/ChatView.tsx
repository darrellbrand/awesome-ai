import {Button} from '@hilla/react-components/Button.js';
import {Notification} from '@hilla/react-components/Notification.js';
import {TextField} from '@hilla/react-components/TextField.js';
import {ChatService} from 'Frontend/generated/endpoints.js';
import {useState} from 'react';
import {MessageList, MessageListItem} from "@hilla/react-components/MessageList";
import {MessageInput} from "@hilla/react-components/MessageInput";

export default function ChatView() {
    const [messages, setMessages] = useState<MessageListItem[]>([]);

    async function sendMessage(message: string) {
        setMessages(messages => [...messages, {
            userName: "You",
            text: message
        }]);
        const response = await ChatService.chat(message);
        setMessages(messages => [...messages, {
            userName: "Assistant",
            text: message
        }]);
    }

    return <div className="p-m flex-col h-full box-border">
        <MessageList items={messages} className="flex-grow"/>
        <MessageInput onSubmit={ e => sendMessage(e.detail.value )}/>
    </div>
}
