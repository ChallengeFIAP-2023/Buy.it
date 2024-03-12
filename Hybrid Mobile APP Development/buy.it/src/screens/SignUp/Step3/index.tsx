import React, { useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { ArrowRight } from 'phosphor-react-native';
import {
  NativeSyntheticEvent,
  Platform,
  TextInputChangeEventData,
  View,
} from 'react-native';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { SignUpRoutes } from '..';

// Theme import
import theme from '@theme/index';

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  CustomDropdown,
  Display,
  DefaultComponent,
  WrapperPage,
} from '@components/index';

// Style import
import {
  Fieldset,
  Subtitle,
  WrapDropdown,
  AlertTextWrapper,
  ContactsWrapper,
} from './styles';
import { ScrollableContent, AlertText } from '@global/styles/index';

type ContactType = 'Email' | 'Whatsapp' | 'Telefone';

interface Contact {
  type: ContactType;
  value: string;
}

interface ContactOptions {
  label: string;
  value: ContactType;
}

const contactOptions = [
  { label: 'Whatsapp', value: 'Whatsapp' },
  { label: 'Email', value: 'Email' },
  { label: 'Telefone', value: 'Telefone' },
];

export const Step3: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step3'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // State
  const [contactType, setContactType] = useState<ContactType>('Email');
  const [contactValue, setContactValue] = useState('');
  const [contacts, setContacts] = useState<Contact[]>([]);

  const removeContact = (index: number) => {
    setContacts(prevContacts => {
      const updatedContacts = [...prevContacts];
      updatedContacts.splice(index, 1);
      return updatedContacts;
    });
  };

  const addContact = () => {
    if (!contactValue) {
      return;
    }

    const newContact: Contact = {
      type: contactType,
      value: contactValue,
    };

    setContacts(prevContacts => [...prevContacts, newContact]);
    setContactValue('');
  };

  const hasContacts = Array.isArray(contacts) && contacts?.length >= 1;

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'Dados de contato',
            subtitle: 'Passo 3 de 5',
          }}
          key="default-component-step2"
        />

        <AlertTextWrapper>
          <AlertText>
            Lembre-se de que os emails fornecidos nesta tela não podem ser
            usados para fazer login. 😉
          </AlertText>
        </AlertTextWrapper>

        <DecreasingContainer scrollable>
          <WrapDropdown>
            <CustomDropdown
              label="Forma de contato"
              placeholder="Selecione uma opção"
              options={contactOptions}
              selectedValue={contactType}
              onValueChange={(value: ContactType) => setContactType(value)}
            />
          </WrapDropdown>

          {contactType && (
            <Fieldset>
              <Input
                placeholder={`Digite o ${contactType.toLowerCase()}`}
                onChange={(e: NativeSyntheticEvent<TextInputChangeEventData>) =>
                  setContactValue(e.nativeEvent.text)
                }
                value={contactValue}
              />
            </Fieldset>
          )}

          {contactValue && (
            <Button label="Adicionar contato" size="SM" onPress={addContact} />
          )}

          {hasContacts && (
            <ContactsWrapper>
              <Subtitle>Meus contatos</Subtitle>

              {contacts.map((contact, index) => (
                <Display
                  key={`${contact.value}-${index}-${Math.random()}`}
                  value={contact.value}
                  label={String(contact.type)}
                  handleDelete={() => removeContact(index)}
                />
              ))}
            </ContactsWrapper>
          )}
        </DecreasingContainer>
      </ScrollableContent>

      {hasContacts && (
        <Button
          label="Continuar"
          size="XL"
          icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
          bottom
          onPress={() => navigation.navigate('Step4')}
        />
      )}
    </WrapperPage>
  );
};
