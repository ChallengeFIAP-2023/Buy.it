import { Fragment } from 'react';
import { ImageSourcePropType } from 'react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
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
  UserAvatar as CompanyImage,
  Chip,
  DefaultComponent,
} from '@components/index';

// Style import
import { Flex, ScrollableContent } from '@global/styles/index';
import {
  CompanyWrapper,
  CompanyData,
  CompanyName,
  CompanyDocument,
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
import { toMaskedCNPJ } from '@utils/masks';

const maximumTags = 8;

export const QuoteProposalSuccess: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteProposalRoutes, 'QuoteProposalSuccess'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // Hook
  const { 
    handleProcessProposal, 
    proposal, 
    handleRedirectSuccessProposal 
  } = useQuoteProposal();

  const imageSource: ImageSourcePropType = proposal?.comprador.urlImagem
    ? { uri: proposal?.comprador.urlImagem }
    : require('../../../assets/default_avatar.png');

  const tags = Array.isArray(proposal?.produto.tags)
    ? proposal?.produto.tags
    : [];

  function deadlineLabel() {
    if (proposal?.prazo && proposal?.prazo > 1)
      return `${proposal?.prazo} dias`;

    return `${proposal?.prazo} dia`;
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
          <DescriptionContainer>
            <CheckCircle
              size={50}
              color={theme.COLORS.GREEN_700}
              weight="fill"
            />

            <CongratulationText>
              Parabéns por aceitar a cotação!
            </CongratulationText>

            <Description>
              Agora só falta entrar em contato com a empresa e finalizar a venda
            </Description>
          </DescriptionContainer>

          <CompanyWrapper>
            <CompanyImage imageSource={imageSource} size="MD" />

            <CompanyData>
              <CompanyName numberOfLines={1}>
                {proposal?.comprador.nome ?? 'Empresa não identificada'}
              </CompanyName>

              <CompanyDocument>
                {toMaskedCNPJ(proposal?.comprador.cnpj ?? '546545645456465456')}
              </CompanyDocument>
            </CompanyData>
          </CompanyWrapper>

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

          <SectionProductDetail>
            <SectionLabel>Email</SectionLabel>
            <SectionValueText>scranton@dundermifflin.com</SectionValueText>
          </SectionProductDetail>

          <SectionProductDetail>
            <SectionLabel>Telefone</SectionLabel>
            <SectionValueText>11 99999-9999</SectionValueText>
          </SectionProductDetail>
        </Container>
      </ScrollableContent>

      <ActionsButton>
        <ActionButton onPress={() => handleProcessProposal('decline')}>
          <Icon name="envelope" size={theme.FONT_SIZE.XL} color={theme.COLORS.WHITE} />
        </ActionButton>

        <ActionButton onPress={() => handleProcessProposal('decline')}>
          <Icon name="whatsapp" size={theme.FONT_SIZE.XXL} color={theme.COLORS.WHITE} />
        </ActionButton>
      </ActionsButton>
    </WrapperPage>
  );
};
