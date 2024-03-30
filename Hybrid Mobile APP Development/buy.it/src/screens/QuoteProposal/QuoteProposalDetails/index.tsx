import { ImageSourcePropType } from 'react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { Check, DotsThree, Truck, X } from 'phosphor-react-native';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteProposalRoutes } from '..';

//Hooks import
import { useAuth } from '@hooks/useAuth';

// Theme import
import theme from '@theme/index';

// Component import
import {
  DecreasingContainer,
  WrapperPage,
  UserAvatar as CompanyImage,
  Chip,
} from '@components/index';

// Style import
import { Flex, ScrollableContent } from '@global/styles/index';
import {
  CompanyWrapper,
  CompanyData,
  CompanyName,
  CompanyDocument,
  ProductDetails,
  DescriptionContainer,
  Description,
  ProductQuantity,
  ProductName,
  PerText,
  TagWrapper,
  ProductPriceContainer,
  Price,
  DeliveryContainer,
  DeliveryText,
  ActionsButton,
  Refuse,
  Accept,
} from './styles';

const tags = ['Material escolar', 'Papelaria'];
const maximumTags = 8;

export const QuoteProposalDetails: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteProposalRoutes, 'QuoteProposalDetails'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // Hook
  const { user } = useAuth();

  const imageSource: ImageSourcePropType = user.urlImagem
    ? { uri: user.urlImagem }
    : require('../../../assets/default_avatar.png');

  return (
    <WrapperPage>
      <ScrollableContent>
        <CompanyWrapper>
          <CompanyImage imageSource={imageSource} size="MD" />

          <CompanyData>
            <CompanyName numberOfLines={1}>Dunder Mifflin</CompanyName>
            <CompanyDocument>92.754.738/0001-62 </CompanyDocument>
          </CompanyData>
        </CompanyWrapper>

        <DescriptionContainer>
          <Description>
            Criou uma cotação que pode ser do seu interesse
          </Description>

          <TagWrapper>
            {tags.map(item => (
              <Chip value={item} key={`${item}-${Math.random()}`} />
            ))}
          </TagWrapper>

          {tags && tags.length >= maximumTags && (
            <Flex style={{ justifyContent: 'flex-start' }}>
              <DotsThree size={40} color={theme.COLORS.WHITE} />
              <Description>Mais {tags.length - maximumTags} tags</Description>
            </Flex>
          )}
        </DescriptionContainer>

        <DecreasingContainer>
          <ProductDetails>
            <ProductQuantity>100 unidades de</ProductQuantity>
            <ProductName>Caneta Esferográfica</ProductName>
            <PerText>por</PerText>

            <ProductPriceContainer>
              <PerText>R$</PerText>
              <Price>0,50</Price>
              <PerText>cada</PerText>
            </ProductPriceContainer>

            <DeliveryContainer>
              <Truck size={32} color={theme.COLORS.WHITE} />
              <DeliveryText>
                Para entrega em{' '}
                <DeliveryText style={{ fontWeight: '800' }}>
                  5 dias
                </DeliveryText>
              </DeliveryText>
            </DeliveryContainer>
          </ProductDetails>
        </DecreasingContainer>
      </ScrollableContent>

      <ActionsButton>
        <Refuse>
          <X size={40} color={theme.COLORS.GRAY_600} weight="bold" />
        </Refuse>

        <Accept>
          <Check size={40} color={theme.COLORS.GRAY_600} weight="bold" />
        </Accept>
      </ActionsButton>
    </WrapperPage>
  );
};
