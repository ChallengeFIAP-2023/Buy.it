import React, { useState } from "react";

// Component import
import {
  DecreasingContainer,
  Highlight,
  Input,
  Button,
  CustomDropdown,
  Display,
  Header
} from "@components/index";

// Style import
import { Container, Fieldset, Subtitle, WrapContacts, WrapDropdown } from './styles';

import { ArrowRight } from "phosphor-react-native";
import { NativeSyntheticEvent, TextInputChangeEventData } from "react-native";

export function Step3({ navigation }: any) {

  const [contactType, setContactType] = useState('Email');
  const [contact, setContact] = useState('');
  
  const mock_contacts = [
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
  ]

  const [contacts, setContacts] = useState(mock_contacts)

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

  return (
    <Container>
      {/* Arrumar esse dois componentes abaixo */}
      {/* <StatusBar /> */}
      <Header />

      <Highlight
        title="Dados de contato"
        subtitle="Passo 3 de 5"
      />

      <DecreasingContainer>
        <WrapDropdown>
          {/* TODO: as opções serão uma busca da tabela tipo_forma_contato */}
          <CustomDropdown
            label="Forma de contato"
            placeholder="Selecione uma opção"
            options={[
              { label: 'Email', value: 'Email' },
              { label: 'Telefone', value: 'Telefone' },
              { label: 'Fax', value: 'Fax' },
            ]}
            selectedValue={contactType}
            onValueChange={(value: string) => setContactType(value)}
          />
        </WrapDropdown>

        {/* TODO: adicionar um mask nesse input */}
        <Fieldset>
          <Input
            label={contactType}
            placeholder={`Digite seu ${contactType.toLowerCase()}`}
            onChange={(e: NativeSyntheticEvent<TextInputChangeEventData>) => setContact(e.nativeEvent.text) }
            value={contact}
          />
        </Fieldset>

        <Button 
          label="Adicionar contato"
          size="SM"
          onPress={addContact}
        />

        {contacts.length > 0 && (
          <>
            <Subtitle>
              Meus contatos
            </Subtitle>

            {/* TODO: quando soubermos exatamente como os dados virão da API, eles serão tipados corretamente eliminando o erro */}
            <WrapContacts
              data={contacts}
              renderItem={({ item }) => { return(
                <Display
                  value={item.valor_forma_contato} 
                  label={item.forma_contato.nome_tipo_contato}
                />
              )}}
              keyExtractor={item => item.id.toString()}
            />
          </>
        )}

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
