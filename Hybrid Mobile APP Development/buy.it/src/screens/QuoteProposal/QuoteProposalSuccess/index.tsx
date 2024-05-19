import { Fragment } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { Linking } from 'react-native'

import {
  CheckCircle,
  DotsThree,
} from 'phosphor-react-native';
import Icon from '@expo/vector-icons/FontAwesome';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteProposalRoutes } from '..';

//Hooks import
import { useQuoteProposal } from '@hooks/useQuoteProposal';

// Theme import
import theme from '@theme/index';

// Component import
import {
  WrapperPage,
  Chip,
  DefaultComponent,
  UserInfo,
} from '@components/index';

// Style import
import { Flex, ScrollableContent } from '@global/styles/index';
import {
  SectionProductDetail,
  DescriptionContainer,
  Description,
  CongratulationText,
  TagWrapper,
  ActionsButton,
  Container,
  SectionLabel,
  SectionValueText,
  ActionButton,
} from './styles';

const maximumTags = 8;

export const QuoteProposalSuccess: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteProposalRoutes, 'QuoteProposalSuccess'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // Hook
  const {
    contacts,
    proposal, 
    handleRedirectSuccessProposal 
  } = useQuoteProposal();

  const email = contacts?.find(contact => contact.tipo === "Email")
  const telephone = contacts?.find(contact => contact.tipo === "Telefone")
  const whatsapp = contacts?.find(contact => contact.tipo === "Whatsapp")

  const tags = Array.isArray(proposal?.produto.tags)
    ? proposal?.produto.tags
    : [];

  const sendEmail = () => {
    const subject = `Cotação de ${proposal?.produto.nome} aceita! Vamos combinar os detalhes?`;
    Linking.openURL(`mailto:${email?.valor}?subject=${subject}`);
  }

  const call = () => {
    Linking.openURL(`tel:${telephone?.valor}`);
  }

  const sendMessage = () => {
    const message = `Cotação de ${proposal?.produto.nome} aceita! Vamos combinar os detalhes?`;
    Linking.openURL(`whatsapp://send?phone=${whatsapp?.valor}&text=${message}`);
  }

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          statusBarProps={{ backgroundColor: theme.COLORS.GRAY_700 }}
          headerProps={{
            goBack: handleRedirectSuccessProposal,
            showLogo: false,
          }}
          key="default-component-quote-proposal-success"
        />

        <Container>
          <UserInfo user={proposal?.comprador}/>

          <DescriptionContainer>
            <CongratulationText>
              Parabéns por aceitar a cotação!
            </CongratulationText>

            <Description>
              Agora só falta entrar em contato com a empresa e finalizar a venda
            </Description>
          </DescriptionContainer>

          <SectionProductDetail>
            <SectionLabel>Tags</SectionLabel>

            {tags[0] && (
              <Fragment>
                <TagWrapper>
                  {tags.map(item => (
                    <Chip
                      value={item.nome}
                      key={`${item.nome}-${Math.random()}`}
                    />
                  ))}
                </TagWrapper>

                {tags && tags.length >= maximumTags && (
                  <Flex style={{ justifyContent: 'flex-start' }}>
                    <DotsThree size={40} color={theme.COLORS.WHITE} />
                    <Description>
                      Mais {tags.length - maximumTags} tags
                    </Description>
                  </Flex>
                )}
              </Fragment>
            )}
          </SectionProductDetail>

          {contacts?.map(contact =>  (
            <SectionProductDetail>
              <SectionLabel>{contact.tipo}</SectionLabel>
              <SectionValueText>{contact.valor}</SectionValueText>
            </SectionProductDetail>
          ))}

        </Container>
      </ScrollableContent>

      <ActionsButton>
        {email && (
          <ActionButton onPress={sendEmail}>
            <Icon name="envelope" size={theme.FONT_SIZE.XL} color={theme.COLORS.WHITE} />
          </ActionButton>
        )}

        {whatsapp && (
          <ActionButton onPress={sendMessage}>
            <Icon name="whatsapp" size={theme.FONT_SIZE.XXL} color={theme.COLORS.WHITE} />
          </ActionButton>
        )}

        {telephone && (
          <ActionButton onPress={call}>
            <Icon name="phone" size={theme.FONT_SIZE.XXL} color={theme.COLORS.WHITE} />
          </ActionButton>
        )}
      </ActionsButton>
    </WrapperPage>
  );
};
