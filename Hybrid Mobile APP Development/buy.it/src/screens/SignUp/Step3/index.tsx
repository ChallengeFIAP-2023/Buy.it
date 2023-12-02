import React, { useState } from "react";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { CompositeScreenProps } from "@react-navigation/native";
import { ArrowRight } from "phosphor-react-native";
import { NativeSyntheticEvent, TextInputChangeEventData } from "react-native";

// Type import
import { MainNavigationRoutes } from "@routes/index";
import { SignUpRoutes } from "..";

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  CustomDropdown,
  Display,
  DefaultComponent,
} from "@components/index";

// Style import
import { Container, Fieldset, Subtitle, WrapDropdown } from './styles';

const contactOptions = [
  { label: 'Whatsapp', value: 'Whatsapp' },
  { label: 'Email', value: 'Email' },
  { label: 'Telefone', value: 'Telefone' },
]

export const Step3: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step3'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // State
  const [contactType, setContactType] = useState('Email');
  const [contact, setContact] = useState('');
  const [contacts, setContacts] = useState([
    {
      id: 1,
      forma_contato: {
        id: 1,
        nome_tipo_contato: 'Email'
      },
      valor_forma_contato: 'mariana@gmail.com'
    },
    {
      id: 2,
      forma_contato: {
        id: 1,
        nome_tipo_contato: 'Email'
      },
      valor_forma_contato: 'kaue@email.com'
    }
  ])

  const addContact = () => {
    const newContact = {
      id: 1,
      forma_contato: {
        id: 1,
        nome_tipo_contato: contactType
      },
      valor_forma_contato: contact
    }

    setContacts(prevContacts => [...prevContacts, newContact]);
    setContact('');
  }

  const hasContacts = Array.isArray(contacts) && contacts?.length >= 1;

  return (
    <Container>
      <DefaultComponent
        headerProps={{ goBack: () => navigation.goBack() }}
        highlightProps={{
          title: "Dados de contato",
          subtitle: "Passo 3 de 5"
        }}
        key="default-component-step2"
      />

      <DecreasingContainer>
        <WrapDropdown>
          <CustomDropdown
            label="Forma de contato"
            placeholder="Selecione uma opção"
            options={contactOptions}
            selectedValue={contactType}
            onValueChange={(value: string) => setContactType(value)}
          />
        </WrapDropdown>

        <Fieldset>
          <Input
            label={contactType}
            placeholder={`Digite seu ${contactType.toLowerCase()}`}
            onChange={(e: NativeSyntheticEvent<TextInputChangeEventData>) => setContact(e.nativeEvent.text)}
            value={contact}
          />
        </Fieldset>

        <Button
          label="Adicionar contato"
          size="SM"
          onPress={addContact}
        />

        {hasContacts && (
          <Subtitle>
            Meus contatos
          </Subtitle>
        )}

        {hasContacts && contacts.map(contact => (
          <Display
            key={contact.id}
            value={contact.valor_forma_contato}
            label={contact.forma_contato.nome_tipo_contato}
          />
        ))}
      </DecreasingContainer>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={'#fff'} weight="bold" />}
        bottom
        onPress={() => navigation.navigate("Step4")}
      />
    </Container>
  );
}
